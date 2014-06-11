package eu.pmsoft.tutorial.parts.part3;

import com.google.inject.Inject;
import com.google.inject.Provider;
import eu.pmsoft.tutorial.guice.api.direct.ArticleService;
import eu.pmsoft.tutorial.guice.api.direct.ExternalLibApi;
import eu.pmsoft.tutorial.guice.model.ArticleServiceFactory;
import eu.pmsoft.tutorial.guice.model.Comment;
import eu.pmsoft.tutorial.guice.model.Result;
import eu.pmsoft.tutorial.guice.model.Task;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

@Guice(modules = {ProvidersModelModule.class})
public class ProvidersModelModuleTest {


    @Inject
    public ProvidersModelModuleTest(ExternalLibApi externalApi, Task task, Provider<Task> taskProvider,
                                    ArticleServiceFactory articleServiceFactory, Provider<Comment> commentProvider,
                                    Provider<Result> resultProvider) {
        this.externalApi = externalApi;
        this.task = task;
        this.taskProvider = taskProvider;
        this.articleServiceFactory = articleServiceFactory;
        this.commentProvider = commentProvider;
        this.resultProvider = resultProvider;
    }

    private final ExternalLibApi externalApi;

    @Test
    void testExternalApi() {
        assertTrue(externalApi.test());
    }

    private final Provider<Task> taskProvider;
    private final Task task;

    @Test
    void testTaskExist() {
        assertNotNull(task);
        assertNotNull(taskProvider.get());
    }


    private final ArticleServiceFactory articleServiceFactory;

    @Test
    void testArticleFactory() {
        ArticleService content = articleServiceFactory.build(1, "content");
        assertNotNull(content);
        assertEquals(content.getApiKey(), "content");
        assertEquals(content.getProviderId(), 1);
    }


    @Inject
    public ArticleService directArticleService;

    @Test
    public void testDomainObjectsInjection() {
        assertNotNull(directArticleService);
        assertTrue(directArticleService.validate());
    }

    private final Provider<Comment> commentProvider;

    private final Provider<Result> resultProvider;

    @Test
    void testCounters() {
        Result result = resultProvider.get();
        Comment comment = commentProvider.get();
        assertNotNull(result);
        assertNotNull(comment);
    }
}