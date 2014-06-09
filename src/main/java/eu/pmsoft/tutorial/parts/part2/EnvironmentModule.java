package eu.pmsoft.tutorial.parts.part2;

import eu.pmsoft.tutorial.guice.api.direct.ModelEnvironment;

import com.google.inject.AbstractModule;

public class EnvironmentModule extends AbstractModule {

    private final ModelEnvironment environment;

    public EnvironmentModule(ModelEnvironment environment) {
        this.environment = environment;
    }

    @Override
    protected void configure() {
        bind(ModelEnvironment.class).toInstance(environment);
    }

}
