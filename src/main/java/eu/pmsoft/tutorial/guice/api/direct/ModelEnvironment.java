package eu.pmsoft.tutorial.guice.api.direct;

import com.google.inject.Inject;

public class ModelEnvironment {

	private final String name;

	@Inject
	public ModelEnvironment() {
		name = "development";
	}

	public ModelEnvironment(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
