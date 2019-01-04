package id.jmlcode.sample.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Jamal on 3/13/2018.
 */
@Scope
@Retention(RUNTIME)
public @interface IntentServiceScope {
}
