package warsjava.guice.multibinding;

import java.util.Map;
import java.util.Set;

import javax.inject.Singleton;

import com.google.inject.Inject;

import eu.pmsoft.tutorial.guice.api.direct.ModelPlugin;

@Singleton
public class ModelPluginRegistry {

	private final Set<ModelPlugin> pluginSet;
	private final Map<String, ModelPlugin> pluginMap;

	@Inject
	public ModelPluginRegistry(Set<ModelPlugin> pluginSet, Map<String, ModelPlugin> pluginMap) {
		super();
		this.pluginSet = pluginSet;
		this.pluginMap = pluginMap;
	}

	public int getPluginCounter() {
		if (pluginSet.size() != pluginMap.size())
			throw new RuntimeException("set and map have different size");
		return pluginSet.size();
	}

}
