package eu.pmsoft.tutorial.guice.apiImplementation.simple;

import eu.pmsoft.tutorial.guice.api.simple.MySuperLibraryApi;
import eu.pmsoft.tutorial.guice.api.direct.TaskExecutionListener;
import eu.pmsoft.tutorial.guice.model.Result;
import eu.pmsoft.tutorial.guice.model.Task;

/**
 * User: paweld2
 * Date: 09.06.14
 */
public class MySuperLibraryEmptyImplementation implements MySuperLibraryApi {


    @Override
    public Task buildTaskDefinition(String definition) {
        return null;
    }

    @Override
    public Result executeTask(Task task) {
        return null;
    }

    @Override
    public void registerListener(TaskExecutionListener globalListener) {

    }
}
