package eu.pmsoft.tutorial.guice.apiImplementation.direct;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import eu.pmsoft.tutorial.guice.api.direct.ArticleBackend;
import eu.pmsoft.tutorial.guice.api.direct.ArticleService;


public class ArticleServiceImpl implements ArticleService {

    private final Integer providerId;
    private final String apiKey;
    private final ArticleBackend backend;

    public boolean validate() {
        if (providerId == null)
            return false;

        return true;
    }

    @Inject @AssistedInject
    public ArticleServiceImpl(ArticleBackend backend, @Assisted("provider") Integer providerId,
                              @Assisted("key") String apiKey) {
        super();
        this.providerId = providerId;
        this.apiKey = apiKey;
        this.backend = backend;
    }

    @Override
    public int getProviderId() {
        return providerId;
    }

    public String getApiKey() {
        return apiKey;
    }

    @Override
    public void someLogicCall() {
        backend.someCall(apiKey,providerId);

    }
}
