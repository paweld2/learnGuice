package eu.pmsoft.tutorial.guice.model;

public interface TaskObserver {

	public boolean observeTask(Task task);
	
	public boolean checkTask(Task task);
}
