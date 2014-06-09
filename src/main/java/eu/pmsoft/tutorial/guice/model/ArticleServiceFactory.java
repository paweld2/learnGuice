package eu.pmsoft.tutorial.guice.model;

import com.google.inject.assistedinject.Assisted;
import eu.pmsoft.tutorial.guice.api.direct.ArticleService;

/**
 * User: paweld2
 * Date: 2014-06-09
 */
public interface ArticleServiceFactory {

    ArticleService build(@Assisted("provider") Integer providerId, @Assisted("key") String apiKey);

}
