package eu.pmsoft.tutorial.parts.part2;

import eu.pmsoft.tutorial.guice.api.direct.InternalModelAPI;
import eu.pmsoft.tutorial.guice.model.TaskObserver;
import eu.pmsoft.tutorial.guice.apiImplementation.direct.InternalModelApiImplementation;
import eu.pmsoft.tutorial.guice.apiImplementation.direct.TaskObserverImplementation;

import com.google.inject.PrivateModule;
import com.google.inject.Scopes;

public class TaskModelModule extends PrivateModule {

	@Override
	protected void configure() {
		bind(TaskObserver.class).to(TaskObserverImplementation.class).in(Scopes.SINGLETON);
		bind(InternalModelAPI.class).to(InternalModelApiImplementation.class);
		expose(TaskObserver.class);
	}

}
