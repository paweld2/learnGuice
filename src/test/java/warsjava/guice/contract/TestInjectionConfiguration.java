package warsjava.guice.contract;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import eu.pmsoft.tutorial.guice.api.direct.ArticleService;
import eu.pmsoft.tutorial.guice.api.direct.ExternalLibApi;
import eu.pmsoft.tutorial.guice.api.direct.LoggingContract;
import eu.pmsoft.tutorial.guice.api.direct.ModelContract;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import eu.pmsoft.tutorial.guice.model.AdminUser;
import eu.pmsoft.tutorial.guice.model.Comment;
import eu.pmsoft.tutorial.guice.model.Task;
import eu.pmsoft.tutorial.guice.apiImplementation.direct.ModelWarsjava;
import eu.pmsoft.tutorial.guice.utils.jit.CardProcessor;
import eu.pmsoft.tutorial.guice.utils.jit.CardValidator;
import eu.pmsoft.tutorial.parts.part2.ModelModule;
import eu.pmsoft.tutorial.guice.utils.annot.MyWorkshopCustomAnnotation;

import com.google.inject.Inject;
import com.google.inject.name.Named;

@Guice(modules = ModelModule.class)
public class TestInjectionConfiguration {

	@Inject
	public LoggingContract simpleLogger;

	@Test(groups = "test1")
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

	@Test(groups = "test1")
	public void testNamedLoggerBind() {
		assertNotNull(namedLevel1);
		assertNotNull(namedLevel2);
		assertEquals(namedLevel1.getLevel(), 1);
		assertEquals(namedLevel2.getLevel(), 2);
		namedLevel1.log("test pass");
		namedLevel2.log("test pass");
	}

	@Inject
	public ModelContract modelRef1;

	@Inject
	public ModelContract modelRef2;

	@Test
	public void testModelIsSingleton() {
		assertNotNull(modelRef1);
		assertNotNull(modelRef2);
		assertEquals(modelRef1.getInstanceNr(), modelRef2.getInstanceNr());
	}

	@Inject
	public ArticleService someArticleService;

	@Inject
	public Comment someComment;

	@Test
	public void testDomainObjectsInjection() {
		assertNotNull(someArticleService);
		assertTrue(someArticleService.validate());
		assertNotNull(someComment);
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
	public AdminUser adminRef1;
	@Inject
	public AdminUser adminRef2;

	@Test
	public void testAdminUserIsSingleton() {
		assertNotNull(adminRef1);
		assertNotNull(adminRef2);
		assertEquals(adminRef1, adminRef2);
	}

	@Inject
	public ExternalLibApi externalApi;

	@Test
	public void testExternalLibBinding() {
		assertNotNull(externalApi);
		assertTrue(externalApi.test());
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

	@Test
	public void testTaskCreationOnModel() {
		Task task1 = modelRef1.getNewTaskInstance();
		Task task2 = modelRef1.getNewTaskInstance();
		assertNotNull(task1);
		assertNotNull(task2);
		assertTrue(task1.getTaskNr().compareTo(task2.getTaskNr()) != 0);

	}
	
	@Inject
	@MyWorkshopCustomAnnotation
	public LoggingContract loggerWithCustomAnnotation;
	
	@Test
	public void testCreationOfCustomBindAnnotation(){
		assertNotNull(loggerWithCustomAnnotation);
		assertEquals(100, loggerWithCustomAnnotation.getLevel());
		loggerWithCustomAnnotation.log("Custom annotation works");
	}
	
	@Test(expectedExceptions = RuntimeException.class)
	public void testForbiddenMethodInterception(){
		modelRef1.forbiddenMethod();
	}
}
