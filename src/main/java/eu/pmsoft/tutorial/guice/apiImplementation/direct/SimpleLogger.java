package eu.pmsoft.tutorial.guice.apiImplementation.direct;

import eu.pmsoft.tutorial.guice.api.direct.LoggingContract;

public class SimpleLogger implements LoggingContract {

	@Override
	public void log(String message) {
		System.out.println("simple:"+message);
	}

	@Override
	public int getLevel() {
		return 0;
	}

}
