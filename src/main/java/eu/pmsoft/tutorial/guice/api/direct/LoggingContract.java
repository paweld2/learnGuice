package eu.pmsoft.tutorial.guice.api.direct;

public interface LoggingContract {

	public void log(String message);
	
	public int getLevel();
}
