package eu.pmsoft.tutorial.parts.part2;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import eu.pmsoft.tutorial.guice.api.direct.ModelContract;
import eu.pmsoft.tutorial.guice.api.direct.ModelEnvironment;
import eu.pmsoft.tutorial.parts.part3.ModelModule;
import eu.pmsoft.tutorial.parts.part3.TaskModelModule;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;


public class EnvironmentModuleTest {

    @Test
    public void testExtendedModelInjectorEnvironment() {
//		Don't allow the use of the default constructor of ModelEnvironment
//		Bind this instance as a singleton to ModelEnvironment
        ModelEnvironment testEnvironment = new ModelEnvironment("test");
        Injector injector = Guice.createInjector(new ModelModule(), new TaskModelModule(),
                new EnvironmentModule(testEnvironment));
        assertNotNull(injector);
        ModelContract model = injector.getInstance(ModelContract.class);
        assertEquals("test", model.getEnvironmentName());
        assertTrue("test".compareTo(model.getEnvironmentName()) == 0);
    }


    public static class ExternalSourceToBeInjected {
        @Inject
        public ModelContract model;

        public boolean testModelInjection() {
            if (model == null) throw new RuntimeException("model is null");
            return model.getEnvironmentName().compareTo("testInjectionOfMembers") == 0;
        }
    }

    @Test
    public void testInjectionOfMembers() {
        ModelEnvironment testEnvironment = new ModelEnvironment("testInjectionOfMembers");
        Module allModel = Modules.combine(new ModelModule(), new TaskModelModule(), new EnvironmentModule
                (testEnvironment));
        Injector injector = Guice.createInjector(Modules.combine(allModel));
        assertNotNull(injector);
        // Instance is created when injector is already build. Inject the members
        ExternalSourceToBeInjected injectMePlease = new ExternalSourceToBeInjected();
        injector.injectMembers(injectMePlease);
        assertTrue(injectMePlease.testModelInjection());

    }
}