package id.jmlcode.sample.di.component;

import android.app.Activity;

import dagger.Component;
import id.jmlcode.sample.di.module.FragmentModule;
import id.jmlcode.sample.di.scope.FragmentScope;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    //void inject(SignUpFragment a);
}
