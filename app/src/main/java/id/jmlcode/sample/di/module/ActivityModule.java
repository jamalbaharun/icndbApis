package id.jmlcode.sample.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import id.jmlcode.sample.di.scope.ActivityScope;

@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
