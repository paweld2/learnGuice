package eu.pmsoft.tutorial.parts.part3;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import eu.pmsoft.tutorial.guice.api.direct.LoggingContract;
import eu.pmsoft.tutorial.guice.apiImplementation.direct.ModelWarsjava;
import eu.pmsoft.tutorial.guice.utils.annot.MyWorkshopCustomAnnotation;
import eu.pmsoft.tutorial.guice.utils.jit.CardProcessor;
import eu.pmsoft.tutorial.guice.utils.jit.CardValidator;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

@Guice(modules = {DirectModelModule.class})
public class DirectModelModuleTest {

    @Inject
    public LoggingContract simpleLogger;

    @Test(groups = "testLoggers")
    public void testSimpleLoggerBind() {
        assertNotNull(simpleLogger);
        assertEquals(simpleLogger.getLevel(), 0);
        simpleLogger.log("test pass");
    }

    @Inject
    @Named("level1")
    public LoggingContract namedLevel1;
    @Inject
    @Named("level2")
    public LoggingContract namedLevel2;

    @Test(groups = "testLoggers")
    public void testNamedLoggerBind() {
        assertNotNull(namedLevel1);
        assertNotNull(namedLevel2);
        assertEquals(namedLevel1.getLevel(), 1);
        assertEquals(namedLevel2.getLevel(), 2);
        namedLevel1.log("test pass");
        namedLevel2.log("test pass");
    }

    @Inject
    @MyWorkshopCustomAnnotation
    public LoggingContract loggerWithCustomAnnotation;

    @Test(groups = "testLoggers")
    public void testCreationOfCustomBindAnnotation() {
        assertNotNull(loggerWithCustomAnnotation);
        assertEquals(100, loggerWithCustomAnnotation.getLevel());
        loggerWithCustomAnnotation.log("Custom annotation works");
    }

    @Inject
    @Named("modelLogger")
    public LoggingContract modelLogger;

    @Test
    public void testModelLoggerBind() {
        assertNotNull(modelLogger);
        assertEquals(modelLogger.getLevel(), 10);
        assertEquals(modelLogger, ModelWarsjava.modelInternalLogger);
        modelLogger.log("test pass");
    }




    @Inject
    public CardProcessor cardProcessor;
    @Inject
    public CardValidator cardValidator;

    @Test
    public void testJustInTimeBinding() {
        // For this exercise don't change the module definition
        assertNotNull(cardProcessor);
        assertNotNull(cardValidator);
        assertTrue(cardValidator.validate());
        assertTrue(cardProcessor.processCard());
    }

}