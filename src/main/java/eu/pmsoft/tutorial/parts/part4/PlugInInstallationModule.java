package eu.pmsoft.tutorial.parts.part4;

import java.util.List;

import eu.pmsoft.tutorial.guice.api.direct.ModelPlugin;

import com.google.inject.AbstractModule;
import com.google.inject.internal.UniqueAnnotations;

public class PlugInInstallationModule extends AbstractModule {

	private final List<ModelPlugin> toInstall;
	
	public PlugInInstallationModule(List<ModelPlugin> toInstall) {
		super();
		this.toInstall = toInstall;
	}

	@Override
	protected void configure() {
		for (ModelPlugin plugin : toInstall) {
			bind(ModelPlugin.class).annotatedWith(UniqueAnnotations.create()).toInstance(plugin);
		}
	}

}
