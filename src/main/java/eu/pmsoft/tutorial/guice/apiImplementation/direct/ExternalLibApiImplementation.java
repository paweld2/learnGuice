package eu.pmsoft.tutorial.guice.apiImplementation.direct;

import eu.pmsoft.tutorial.guice.api.direct.ExternalLibApi;

public class ExternalLibApiImplementation implements ExternalLibApi {

	private final boolean status;

	/**
	 * Assume that this class is provided by a external library, so it is no
	 * possible to change this class definition
	 */
	public ExternalLibApiImplementation() {
		status = true;
	}

	public ExternalLibApiImplementation(boolean status) {
		this.status = status;
	}

	@Override
	public boolean test() {
		return status;
	}

}
