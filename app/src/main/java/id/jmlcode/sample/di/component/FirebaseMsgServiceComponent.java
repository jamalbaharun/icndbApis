package id.jmlcode.sample.di.component;

import dagger.Component;
import id.jmlcode.sample.di.module.FirebaseMsgServiceModule;
import id.jmlcode.sample.di.scope.FirebaseMsgServiceScope;
import id.jmlcode.sample.pushnotification.FcmListenerService;

/**
 * Created by Jamal on 9/14/2017.
 */

@FirebaseMsgServiceScope
@Component(dependencies = AppComponent.class, modules = FirebaseMsgServiceModule.class)
public interface FirebaseMsgServiceComponent {
    void inject(FcmListenerService a);
}
