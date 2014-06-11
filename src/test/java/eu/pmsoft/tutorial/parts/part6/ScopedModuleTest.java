package eu.pmsoft.tutorial.parts.part6;

import com.google.inject.Inject;
import com.google.inject.Injector;
import eu.pmsoft.tutorial.parts.part6.ScopeController;
import eu.pmsoft.tutorial.parts.part6.ScopedModule;
import eu.pmsoft.tutorial.parts.part6.ScopedService;
import eu.pmsoft.tutorial.parts.part6.Session;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Guice(modules = {ScopedModule.class})
public class ScopedModuleTest {

    private final Injector injector;

    @Inject
    public ScopedModuleTest(Injector injector) {
        this.injector = injector;
    }

    @Test
    public void testControlledScopeConstruction() {
        ScopeController controller = injector.getInstance(ScopeController.class);
        ScopedService service = injector.getInstance(ScopedService.class);

        assertTrue(service.checkThatIsEmpty());

        controller.enterScope(Session.withId(1));
        assertTrue(service.checkSessionExists(1));
        controller.exitScope();

        service.checkThatIsEmpty();

        controller.enterScope(Session.withId(2));
        assertTrue(service.checkSessionExists(2));
        controller.exitScope();

    }
}