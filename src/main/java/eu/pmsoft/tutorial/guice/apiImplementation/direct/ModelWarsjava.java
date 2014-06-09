package eu.pmsoft.tutorial.guice.apiImplementation.direct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import eu.pmsoft.tutorial.guice.utils.aop.ForbiddenMethod;
import eu.pmsoft.tutorial.guice.api.direct.LoggingContract;
import eu.pmsoft.tutorial.guice.api.direct.ModelContract;
import eu.pmsoft.tutorial.guice.api.direct.ModelEnvironment;
import eu.pmsoft.tutorial.guice.api.direct.ModelPlugin;
import eu.pmsoft.tutorial.guice.model.Task;

import com.google.inject.Binding;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;

public class ModelWarsjava implements ModelContract {

	private final ModelEnvironment environment;

	private final Provider<Task> taskProvider;

	@Inject
	public ModelWarsjava(ModelEnvironment environment, Provider<Task> taskProvider, Injector injector) {
		super();
		this.environment = environment;
		this.taskProvider = taskProvider;
		List<Binding<ModelPlugin>> toInstall = injector.findBindingsByType(TypeLiteral.get(ModelPlugin.class));
		for (Binding<ModelPlugin> binding : toInstall) {
			ModelPlugin plugin = binding.getProvider().get();
			installedPlugins.put(plugin.getName(), plugin);
		}
	}

	Map<String, ModelPlugin> installedPlugins = new HashMap<String, ModelPlugin>();

	@Override
	public int getNrOfPluginInstalled() {
		return installedPlugins.size();
	}

	public static LoggingContract modelInternalLogger = new LoggingContract() {
		@Override
		public void log(String message) {
			System.out.println("model logger:" + message);
		}

		@Override
		public int getLevel() {
			return 10;
		}
	};

	private static AtomicInteger instanceCounter = new AtomicInteger(0);

	private final int instanceNr = instanceCounter.addAndGet(1);

	@Override
	public int getInstanceNr() {
		return instanceNr;
	}

	@Override
	public Task getNewTaskInstance() {
		return taskProvider.get();
	}

	@Override
	@ForbiddenMethod
	public boolean forbiddenMethod() {
		return true;
	}

	@Override
	public String getEnvironmentName() {
		return environment.getName();
	}

}
