package warsjava.guice.contract;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedList;
import java.util.List;

import eu.pmsoft.tutorial.guice.api.direct.InternalModelAPI;
import eu.pmsoft.tutorial.guice.api.direct.LoggingContract;
import eu.pmsoft.tutorial.guice.api.direct.ModelContract;
import eu.pmsoft.tutorial.guice.api.direct.ModelEnvironment;
import eu.pmsoft.tutorial.guice.api.direct.ModelPlugin;
import org.testng.annotations.Test;

import eu.pmsoft.tutorial.guice.model.Task;
import eu.pmsoft.tutorial.guice.model.TaskObserver;
import eu.pmsoft.tutorial.parts.part2.EnvironmentModule;
import eu.pmsoft.tutorial.parts.part2.ModelModule;
import eu.pmsoft.tutorial.parts.part2.PlugInInstalationModule;
import eu.pmsoft.tutorial.parts.part2.TaskModelModule;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;

public class TestInjectorCreation {

	private Injector createModelInjector() {
		return Guice.createInjector(new ModelModule());
	}
	
	@Test
	public void testModelInjectorCreation(){
		Injector injector = createModelInjector();
		assertNotNull(injector);
		assertNotNull(injector.getExistingBinding(Key.get(LoggingContract.class)));
		assertTrue(injector.getExistingBinding(Key.get(LoggingContract.class, Names.named("notExistingBinding"))) == null);
	}
	
	private Injector createExtendedModelInjector(){
		return Guice.createInjector(new ModelModule(),new TaskModelModule());
	}
	
	@Test
	public void testExtendedModelInjectorCreation(){
		// Don't change the definition of module ModelModule
		Injector injector = createExtendedModelInjector();
		assertNotNull(injector);
		assertNotNull(injector.getExistingBinding(Key.get(LoggingContract.class)));
		assertNotNull(injector.getExistingBinding(Key.get(TaskObserver.class)));
		Task task = createTask(injector);
		TaskObserver observer = createObserver(injector);
		assertTrue(observer.observeTask(task));
	}

	private TaskObserver createObserver(Injector injector) {
		return injector.getInstance(TaskObserver.class);
	}

	private Task createTask(Injector injector) {
		return injector.getInstance(Task.class);
	}

	@Test
	public void testExtendedModelInjectorDontProvideInternalApi(){
		Injector injector = createExtendedModelInjector();
		assertNotNull(injector);
		assertNotNull(injector.getExistingBinding(Key.get(LoggingContract.class)));
		assertNotNull(injector.getExistingBinding(Key.get(TaskObserver.class)));
		assertTrue(injector.getExistingBinding(Key.get(InternalModelAPI.class))==null);
		Task task = createTask(injector);
		TaskObserver observer = createObserver(injector);
		assertTrue(observer.checkTask(task));
	}
	
	@Test
	public void testExtendedModelInjectorEnvironment(){
//		Don't allow the use of the default constructor of ModelEnvironment
//		Bind this instance as a singleton to ModelEnvironment
		ModelEnvironment testEnvironment = new ModelEnvironment("test");
		Injector injector = Guice.createInjector(new ModelModule(),new TaskModelModule(), new EnvironmentModule(testEnvironment));
		assertNotNull(injector);
		ModelContract model = injector.getInstance(ModelContract.class);
		assertEquals("test", model.getEnvironmentName());
		assertTrue("test".compareTo(model.getEnvironmentName())==0);
	}
	
	@Test
	public void testPlugInInstalation(){
//		Install this plugins on the model
		List<ModelPlugin> plugins1 = pluginsToInstall("provider1");
		List<ModelPlugin> plugins2 = pluginsToInstall("provider2");

		ModelEnvironment testEnvironment = new ModelEnvironment("test");
		Module allModel = Modules.combine(new ModelModule(),new TaskModelModule(), new EnvironmentModule(testEnvironment));
		Module pluginsModules = Modules.combine(new PlugInInstalationModule(plugins1),new PlugInInstalationModule(plugins2));
		
		Injector injector = Guice.createInjector(Modules.combine(allModel,pluginsModules));
		assertNotNull(injector);
		ModelContract model = injector.getInstance(ModelContract.class);
		assertEquals(10, model.getNrOfPluginInstalled());
	}
	
	private List<ModelPlugin> pluginsToInstall(final String provider){
		List<ModelPlugin> list = new LinkedList<ModelPlugin>();
		list.add(new ModelPlugin() {
			@Override
			public String getName() {
				return provider+"-plugin1";
			}
		});
		list.add(new ModelPlugin() {
			@Override
			public String getName() {
				return provider+"-plugin2";
			}
		});
		list.add(new ModelPlugin() {
			@Override
			public String getName() {
				return provider+"-plugin3";
			}
		});
		list.add(new ModelPlugin() {
			@Override
			public String getName() {
				return provider+"-plugin4";
			}
		});
		list.add(new ModelPlugin() {
			@Override
			public String getName() {
				return provider+"-plugin5";
			}
		});
		return list;
	}
	
	@Test
	public void testInjectionOfMembers(){
		ModelEnvironment testEnvironment = new ModelEnvironment("testInjectionOfMembers");
		Module allModel = Modules.combine(new ModelModule(),new TaskModelModule(), new EnvironmentModule(testEnvironment));
		Injector injector = Guice.createInjector(Modules.combine(allModel));
		assertNotNull(injector);
		// Instance is created when injector is already build. Inject the members
		ExternalSourceToBeInjected injectMePlease = new ExternalSourceToBeInjected();
		injector.injectMembers(injectMePlease);
		assertTrue(injectMePlease.testModelInjection());
		
	}
	
	public static class ExternalSourceToBeInjected {
		@Inject
		public ModelContract model;
		
		public boolean testModelInjection(){
			if(model == null) throw new RuntimeException("model is null");
			return model.getEnvironmentName().compareTo("testInjectionOfMembers")==0;
		}
	}
}
