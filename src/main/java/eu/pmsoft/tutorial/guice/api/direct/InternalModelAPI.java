package eu.pmsoft.tutorial.guice.api.direct;

import eu.pmsoft.tutorial.guice.model.Task;

public interface InternalModelAPI {

	public boolean makeInternalRequest();

	public boolean validate(Task task);
	
}
