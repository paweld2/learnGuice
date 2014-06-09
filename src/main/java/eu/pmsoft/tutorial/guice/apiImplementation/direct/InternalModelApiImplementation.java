package eu.pmsoft.tutorial.guice.apiImplementation.direct;

import eu.pmsoft.tutorial.guice.api.direct.InternalModelAPI;
import eu.pmsoft.tutorial.guice.model.Task;

public class InternalModelApiImplementation implements InternalModelAPI {

	@Override
	public boolean makeInternalRequest() {
		return true;
	}

	@Override
	public boolean validate(Task task) {
		if( task == null) throw new RuntimeException("taks is null in internal api");
		return true;
	}

}
