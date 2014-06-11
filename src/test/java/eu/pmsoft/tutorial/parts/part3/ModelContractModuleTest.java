package eu.pmsoft.tutorial.parts.part3;

import com.google.inject.Inject;
import eu.pmsoft.tutorial.guice.api.direct.ModelContract;
import eu.pmsoft.tutorial.guice.model.Task;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

@Guice(modules = {ModelContractModule.class,ProvidersModelModule.class})
public class ModelContractModuleTest {

    @Inject
    public ModelContract modelRef1;

    @Inject
    public ModelContract modelRef2;

    @Test
    public void testModelIsSingleton() {
        assertNotNull(modelRef1);
        assertNotNull(modelRef2);
        assertEquals(modelRef1.getInstanceNr(), modelRef2.getInstanceNr());
    }

    @Test
    public void testTaskCreationOnModel() {
        Task task1 = modelRef1.getNewTaskInstance();
        Task task2 = modelRef1.getNewTaskInstance();
        assertNotNull(task1);
        assertNotNull(task2);
        assertTrue(task1.getTaskNr().compareTo(task2.getTaskNr()) != 0);

    }

}