package id.jmlcode.sample.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Jamal on 9/14/2017.
 */
@Scope
@Retention(RUNTIME)
public @interface FirebaseMsgServiceScope {
}
