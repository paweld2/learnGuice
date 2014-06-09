package eu.pmsoft.tutorial.parts.part1;

import com.google.inject.name.Named;
import eu.pmsoft.tutorial.guice.api.simple.MySuperLibraryApi;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.testng.Assert.*;

@Guice(modules = {SimpleEmptyLibraryModule.class})
public class SimpleEmptyLibraryModuleNGTest {

    private final MySuperLibraryApi impl;

    private final MySuperLibraryApi named;

    @Inject
    public SimpleEmptyLibraryModuleNGTest(
            MySuperLibraryApi impl,
            @Named("named") MySuperLibraryApi named) {
        this.impl = impl;
        this.named = named;
    }

    @Test
    public void testInstanceShouldBeInjected() {
        assertNotNull(impl);
        assertNotNull(named);
    }

}