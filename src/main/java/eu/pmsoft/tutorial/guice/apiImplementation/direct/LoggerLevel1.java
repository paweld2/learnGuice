package eu.pmsoft.tutorial.guice.apiImplementation.direct;

import eu.pmsoft.tutorial.guice.api.direct.LoggingContract;

public class LoggerLevel1 implements LoggingContract {

	@Override
	public void log(String message) {
		System.out.println("1:"+message);
	}

	@Override
	public int getLevel() {
		return 1;
	}

}
