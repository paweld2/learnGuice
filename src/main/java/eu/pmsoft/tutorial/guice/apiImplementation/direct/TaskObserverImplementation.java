package eu.pmsoft.tutorial.guice.apiImplementation.direct;

import com.google.inject.Inject;

import eu.pmsoft.tutorial.guice.api.direct.InternalModelAPI;
import eu.pmsoft.tutorial.guice.model.Task;
import eu.pmsoft.tutorial.guice.model.TaskObserver;

public class TaskObserverImplementation implements TaskObserver {

	private final InternalModelAPI internalApi;
	
	@Inject
	public TaskObserverImplementation(InternalModelAPI internalApi) {
		this.internalApi = internalApi;
	}


	@Override
	public boolean observeTask(Task task) {
		if(task == null) throw new RuntimeException("task is null");
		return true;
	}


	@Override
	public boolean checkTask(Task task) {
		if(internalApi == null) throw new RuntimeException("internalApi is null");
		return internalApi.validate(task);
	}

}
