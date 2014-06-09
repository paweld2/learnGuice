package eu.pmsoft.tutorial.parts.part2;

import eu.pmsoft.tutorial.guice.utils.aop.ForbiddenMethod;
import eu.pmsoft.tutorial.guice.utils.aop.ForbiddenMethodInterceptor;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class ModelModule extends AbstractModule {

	@Override
	protected void configure() {

        install(new DirectModelModule());

        install(new ProvidersModelModule());

		bindInterceptor(Matchers.any(), Matchers.annotatedWith(ForbiddenMethod.class), new ForbiddenMethodInterceptor());
		
	}


}
