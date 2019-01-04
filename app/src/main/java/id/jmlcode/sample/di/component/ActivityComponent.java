package id.jmlcode.sample.di.component;

import android.app.Activity;

import dagger.Component;
import id.jmlcode.sample.di.module.ActivityModule;
import id.jmlcode.sample.di.scope.ActivityScope;
import id.jmlcode.sample.ui.icndb.IcndbActivity;
import id.jmlcode.sample.ui.activity.MainActivity;
import id.jmlcode.sample.ui.activity.SecondActivity;
import id.jmlcode.sample.ui.login.LoginActivity;


@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(SecondActivity a);

    void inject(MainActivity a);

    void inject(LoginActivity a);

    void inject(IcndbActivity a);
}
