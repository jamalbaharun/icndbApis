package id.jmlcode.sample.di.component;

import dagger.Component;
import id.jmlcode.sample.di.module.IntentServiceModule;
import id.jmlcode.sample.di.scope.IntentServiceScope;
import id.jmlcode.sample.ui.activity.MyIntentService;

/**
 * Created by Jamal on 3/13/2018.
 */
@IntentServiceScope
@Component(dependencies = AppComponent.class, modules = IntentServiceModule.class)
public interface IntentServiceComponent {
    void inject(MyIntentService myIntentService);
}
