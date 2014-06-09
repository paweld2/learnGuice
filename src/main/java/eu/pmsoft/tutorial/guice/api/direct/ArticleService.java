package eu.pmsoft.tutorial.guice.api.direct;

/**
 * User: paweld2
 * Date: 2014-06-09
 */
public interface ArticleService {
    int getProviderId();
    String getApiKey();
    void someLogicCall();
    boolean validate();
}
