package eu.pmsoft.tutorial.guice.api.direct;

import eu.pmsoft.tutorial.guice.model.Task;

public interface ModelContract {

	public int getInstanceNr();
	
	public Task getNewTaskInstance();
	
	public boolean forbiddenMethod();
	
	public String getEnvironmentName();
	
	public int getNrOfPluginInstalled();
}
