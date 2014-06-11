package eu.pmsoft.tutorial.guice.apiImplementation.direct;

import eu.pmsoft.tutorial.guice.api.direct.ForbiddenApi;
import eu.pmsoft.tutorial.guice.utils.aop.ForbiddenMethod;

/**
 * User: paweld2
 * Date: 2014-06-11
 */
public class ForbiddenApiImpl implements ForbiddenApi {
    @Override
    @ForbiddenMethod
    public boolean mustBeForbidden() {
        return true;
    }
}
