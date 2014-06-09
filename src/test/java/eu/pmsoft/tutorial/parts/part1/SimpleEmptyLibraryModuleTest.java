package eu.pmsoft.tutorial.parts.part1;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import eu.pmsoft.tutorial.guice.api.direct.LoggingContract;
import eu.pmsoft.tutorial.guice.api.simple.MySuperLibraryApi;
import eu.pmsoft.tutorial.guice.apiImplementation.simple.MySuperLibraryEmptyImplementation;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


/**
 * User: paweld2
 * Date: 09.06.14
 */
public class SimpleEmptyLibraryModuleTest {

    @Test
    public void testInjectorCreation() {
        Injector injector = Guice.createInjector(new SimpleEmptyLibraryModule());
        assertNotNull(injector);
        assertNotNull(injector.getExistingBinding(Key.get(MySuperLibraryApi.class)));
        assertNotNull(injector.getExistingBinding(Key.get(MySuperLibraryEmptyImplementation.class)));
        assertNotNull(injector.getExistingBinding(Key.get(MySuperLibraryApi.class, Names.named("named"))));

        assertNull(injector.getExistingBinding(Key.get(MySuperLibraryEmptyImplementation.class, Names.named("named"))));
        assertNull(injector.getExistingBinding(Key.get(LoggingContract.class, Names.named("named"))));
    }
}
