package eu.pmsoft.tutorial.guice.apiImplementation.direct;

import java.util.concurrent.atomic.AtomicInteger;

import eu.pmsoft.tutorial.guice.model.Task;

import com.google.inject.Provider;

public class TaskProvider implements Provider<Task>{

	private final AtomicInteger counter = new AtomicInteger();
	
	@Override
	public Task get() {
		return new Task(counter.addAndGet(1));
	}

}
