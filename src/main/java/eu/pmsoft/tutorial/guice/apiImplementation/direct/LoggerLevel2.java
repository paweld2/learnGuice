package eu.pmsoft.tutorial.guice.apiImplementation.direct;

import eu.pmsoft.tutorial.guice.api.direct.LoggingContract;

public class LoggerLevel2 implements LoggingContract {

	@Override
	public void log(String message) {
		System.out.println("2:"+message);
	}

	@Override
	public int getLevel() {
		return 2;
	}

}
