package warsjava.guice.contract;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import javax.inject.Singleton;

import org.testng.annotations.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class TestAssistetInjection {

	public static interface TaskService {

	}

	@Singleton
	public static class TaskServiceImpl implements TaskService {

	}

	public static class DomainServiceTask {

		final String name;
		final TaskService taskService;

		@Inject
		public DomainServiceTask(@Assisted String name, TaskService taskService) {
			this.name = name;
			this.taskService = taskService;
		}
	}

	public static interface DomainServiceTaskFactory {

		public DomainServiceTask getTask(String name);

	}

	public static class CustomDomainServiceTaskFactory implements DomainServiceTaskFactory {

		@Inject
		Provider<TaskService> taskService;

		@Override
		public DomainServiceTask getTask(String name) {
			DomainServiceTask task = new DomainServiceTask(name,taskService.get());
			return task;
		}

	}

	@Test
	public void testCreateAndBindImplementationOfDomainServiceTaskFactory() {
		Injector injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(TaskService.class).to(TaskServiceImpl.class);
				bind(DomainServiceTaskFactory.class).to(CustomDomainServiceTaskFactory.class);
			}
		});
		DomainServiceTaskFactory factory = injector.getInstance(DomainServiceTaskFactory.class);
		DomainServiceTask task = factory.getTask("test name");
		assertNotNull(task);
		assertEquals("test name", task.name);
	}

	@Test
	public void testUserAssitedInjection() {
		Injector injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(TaskService.class).to(TaskServiceImpl.class);
				install(new FactoryModuleBuilder().build(DomainServiceTaskFactory.class));
			}
		});
		DomainServiceTaskFactory factory = injector.getInstance(DomainServiceTaskFactory.class);
		DomainServiceTask task = factory.getTask("test name");
		assertNotNull(task);
		assertEquals("test name", task.name);
	}
}
