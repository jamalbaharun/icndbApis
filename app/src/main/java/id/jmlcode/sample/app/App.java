package id.jmlcode.sample.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import id.jmlcode.sample.di.component.AppComponent;
import id.jmlcode.sample.di.component.DaggerAppComponent;
import id.jmlcode.sample.di.module.AppModule;
import id.jmlcode.sample.di.module.HttpModule;
import id.jmlcode.sample.util.AppBlockCanaryContext;
import io.fabric.sdk.android.Fabric;
import io.realm.Realm;


public class App extends Application {
    private static App instance;
    public static AppComponent appComponent;

    public static synchronized App getInstance() {
        return instance;
    }

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setUpFabric();
//        setUpLeakcanary();
//        setUpBlockLeaknary();
        setUpStetho();
        setUpRealm();
    }

    protected void setUpRealm(){
        Realm.init(getApplicationContext());
    }

    protected void setUpFabric(){
//        Fabric.with(this, new Crashlytics());

        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)  // Enables Crashlytics debugger
                .build();
        Fabric.with(fabric);
    }

    protected void setUpLeakcanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    protected void setUpBlockLeaknary(){
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
    }

    protected void setUpStetho(){
        try {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }
}
