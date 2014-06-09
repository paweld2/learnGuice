package eu.pmsoft.tutorial.guice.api.simple;

import eu.pmsoft.tutorial.guice.api.direct.TaskExecutionListener;
import eu.pmsoft.tutorial.guice.model.Result;
import eu.pmsoft.tutorial.guice.model.Task;

/**
 * User: paweld2
 * Date: 09.06.14
 */
public interface MySuperLibraryApi {

    Task buildTaskDefinition(String definition);

    Result executeTask(Task task);

    void registerListener(TaskExecutionListener globalListener);
}
