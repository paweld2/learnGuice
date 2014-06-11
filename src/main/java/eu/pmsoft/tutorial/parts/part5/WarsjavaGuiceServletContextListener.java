package eu.pmsoft.tutorial.parts.part5;

import eu.pmsoft.tutorial.guice.api.direct.ModelEnvironment;
import eu.pmsoft.tutorial.parts.part2.EnvironmentModule;
import eu.pmsoft.tutorial.parts.part3.ModelModule;
import eu.pmsoft.tutorial.parts.part3.TaskModelModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.util.Modules;

public class WarsjavaGuiceServletContextListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		ModelEnvironment testEnvironment = new ModelEnvironment("servlet");
		Module allModel = Modules.combine(new ModelModule(),new TaskModelModule(), new EnvironmentModule(testEnvironment));
		return Guice.createInjector(Modules.combine(allModel, new WarsjavaServletConfigurationModule()));
	}

}
