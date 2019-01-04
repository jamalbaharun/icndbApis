package id.jmlcode.sample.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Jamal on 9/6/2017.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface IcndbUrl {
}
