package id.jmlcode.sample.di.component;

import javax.inject.Singleton;

import dagger.Component;
import id.jmlcode.sample.app.App;
import id.jmlcode.sample.di.module.AppModule;
import id.jmlcode.sample.di.module.HttpModule;
import id.jmlcode.sample.model.DataManager;
import id.jmlcode.sample.model.db.RealmHelper;
import id.jmlcode.sample.model.http.RetrofitHelper;
import id.jmlcode.sample.model.prefs.ImplPreferencesHelper;


@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();

    DataManager getDataManager();

    RetrofitHelper retrofitHelper();

    RealmHelper realmHelper();

    ImplPreferencesHelper preferencesHelper();
}
