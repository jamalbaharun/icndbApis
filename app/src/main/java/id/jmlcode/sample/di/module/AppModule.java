package id.jmlcode.sample.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.jmlcode.sample.app.App;
import id.jmlcode.sample.model.DataManager;
import id.jmlcode.sample.model.db.DBHelper;
import id.jmlcode.sample.model.db.RealmHelper;
import id.jmlcode.sample.model.http.HttpHelper;
import id.jmlcode.sample.model.http.RetrofitHelper;
import id.jmlcode.sample.model.prefs.ImplPreferencesHelper;
import id.jmlcode.sample.model.prefs.PreferencesHelper;


@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper DBHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, DBHelper, preferencesHelper);
    }
}
