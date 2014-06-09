package eu.pmsoft.tutorial.parts.part1;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import eu.pmsoft.tutorial.guice.api.simple.MySuperLibraryApi;
import eu.pmsoft.tutorial.guice.apiImplementation.simple.MySuperLibraryEmptyImplementation;

/**
 * User: paweld2
 * Date: 09.06.14
 */
public class SimpleEmptyLibraryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MySuperLibraryApi.class).to(MySuperLibraryEmptyImplementation.class);
        bind(MySuperLibraryApi.class).annotatedWith(Names.named("named")).toInstance(new
                MySuperLibraryEmptyImplementation());
    }
}
