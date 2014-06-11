package eu.pmsoft.tutorial.parts.part3;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import eu.pmsoft.tutorial.guice.api.direct.ArticleBackend;
import eu.pmsoft.tutorial.guice.api.direct.ArticleService;
import eu.pmsoft.tutorial.guice.api.direct.ExternalLibApi;
import eu.pmsoft.tutorial.guice.apiImplementation.direct.ArticleBackendImpl;
import eu.pmsoft.tutorial.guice.apiImplementation.direct.ExternalLibApiImplementation;
import eu.pmsoft.tutorial.guice.apiImplementation.direct.TaskProvider;
import eu.pmsoft.tutorial.guice.apiImplementation.direct.ArticleServiceImpl;
import eu.pmsoft.tutorial.guice.model.ArticleServiceFactory;
import eu.pmsoft.tutorial.guice.model.Comment;
import eu.pmsoft.tutorial.guice.model.Result;
import eu.pmsoft.tutorial.guice.model.Task;

/**
 * User: paweld2
 * Date: 2014-06-09
 */
public class ProvidersModelModule extends AbstractModule {
    @Override
    protected void configure() {

        try {
            bind(ExternalLibApi.class).toConstructor(ExternalLibApiImplementation.class.getConstructor());
        } catch (Exception e) {
            addError(e);
        }

        bind(Task.class).toProvider(new TaskProvider());


        bind(ArticleBackend.class).to(ArticleBackendImpl.class);
        install(new FactoryModuleBuilder()
                .implement(ArticleService.class, ArticleServiceImpl.class)
                .build(ArticleServiceFactory.class));
    }

    @Provides
    public ArticleService buildDefaultService(ArticleServiceFactory factory) {
        return factory.build(0, "no-key");
    }

    @Provides
    public Comment getComment() {
        return new Comment(1, "content");
    }

    @Provides
    public Result getResult() {
        return new Result(2);
    }

}
