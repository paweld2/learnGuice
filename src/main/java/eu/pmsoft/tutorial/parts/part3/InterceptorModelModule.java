package eu.pmsoft.tutorial.parts.part3;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import eu.pmsoft.tutorial.guice.api.direct.ForbiddenApi;
import eu.pmsoft.tutorial.guice.apiImplementation.direct.ForbiddenApiImpl;
import eu.pmsoft.tutorial.guice.utils.aop.ForbiddenMethod;
import eu.pmsoft.tutorial.guice.utils.aop.ForbiddenMethodInterceptor;

/**
 * User: paweld2
 * Date: 2014-06-11
 */
public class InterceptorModelModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(ForbiddenApi.class).to(ForbiddenApiImpl.class);

        bindInterceptor(Matchers.any(), Matchers.annotatedWith(ForbiddenMethod.class), new ForbiddenMethodInterceptor());

    }
}
