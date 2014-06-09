package eu.pmsoft.tutorial.parts.part2;

import com.google.inject.Inject;
import com.google.inject.Provider;
import eu.pmsoft.tutorial.guice.api.direct.ArticleService;
import eu.pmsoft.tutorial.guice.api.direct.ExternalLibApi;
import eu.pmsoft.tutorial.guice.apiImplementation.direct.ArticleServiceImpl;
import eu.pmsoft.tutorial.guice.model.ArticleServiceFactory;
import eu.pmsoft.tutorial.guice.model.Comment;
import eu.pmsoft.tutorial.guice.model.Result;
import eu.pmsoft.tutorial.guice.model.Task;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

@Guice(modules = {ProvidersModelModule.class})
public class ProvidersModelModuleTest {

    private final ExternalLibApi externalApi;

    private final Task task;

    private final Provider<Task> taskProvider;

    private final ArticleServiceFactory articleServiceFactory;

    private final Provider<Comment> commentProvider;

    private final Provider<Result> resultProvider;


    @Inject
    public ProvidersModelModuleTest(ExternalLibApi externalApi, Task task, Provider<Task> taskProvider,
                                    ArticleServiceFactory articleServiceFactory, Provider<Comment> commentProvider, Provider<Result
            > resultProvider) {
        this.externalApi = externalApi;
        this.task = task;
        this.taskProvider = taskProvider;
        this.articleServiceFactory = articleServiceFactory;
        this.commentProvider = commentProvider;
        this.resultProvider = resultProvider;
    }

    @Test
    void testExternalApi() {
        assertTrue(externalApi.test());
    }

    @Test
    void testTaskExist() {
        assertNotNull(task);
    }

    @Test
    void testTaskProvider() {
        assertNotNull(taskProvider.get());
    }

    @Test
    void testArticleFactory() {
        ArticleService content = articleServiceFactory.build(1, "content");
        assertNotNull(content);
        assertEquals(content.getApiKey(), "content");
        assertEquals(content.getProviderId(), 1);
    }

    @Test
    void testCounters() {
        Result result = resultProvider.get();
        Comment comment = commentProvider.get();

        assertEquals(result.getIdCounter(), comment.getId());
    }
}