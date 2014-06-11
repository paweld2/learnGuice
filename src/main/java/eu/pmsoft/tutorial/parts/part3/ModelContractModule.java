package eu.pmsoft.tutorial.parts.part3;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import eu.pmsoft.tutorial.guice.api.direct.ModelContract;
import eu.pmsoft.tutorial.guice.apiImplementation.direct.ModelWarsjava;
import eu.pmsoft.tutorial.guice.model.AdminUser;

/**
 * User: paweld2
 * Date: 2014-06-11
 */
public class ModelContractModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ModelContract.class).to(ModelWarsjava.class).in(Singleton.class);
        bind(AdminUser.class).in(Singleton.class);
    }
}
