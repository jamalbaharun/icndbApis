package id.jmlcode.sample.di.module;

import android.app.IntentService;

import dagger.Module;

/**
 * Created by Jamal on 3/13/2018.
 */

@Module
public class IntentServiceModule {
    IntentService intentService;

    public IntentServiceModule(IntentService intentService) {
        this.intentService = intentService;
    }
}
