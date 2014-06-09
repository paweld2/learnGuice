package eu.pmsoft.tutorial.guice.api.direct;

import eu.pmsoft.tutorial.guice.model.Task;

/**
 * User: paweld2
 * Date: 09.06.14
 */
public interface TaskExecutionListener {

    void taskStarting(Task task);

    void taskEnding(Task task);
}
