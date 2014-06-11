package eu.pmsoft.tutorial.parts.part3;

import com.google.inject.AbstractModule;

public class ModelModule extends AbstractModule {

	@Override
	protected void configure() {

        install(new DirectModelModule());

        install(new ProvidersModelModule());

        install(new ModelContractModule());

        install(new InterceptorModelModule());

	}


}
