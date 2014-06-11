package eu.pmsoft.tutorial.parts.part3;

import com.google.inject.Inject;
import eu.pmsoft.tutorial.guice.api.direct.ForbiddenApi;
import eu.pmsoft.tutorial.guice.utils.aop.ForbiddenException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = {InterceptorModelModule.class})
public class InterceptorModelModuleTest {

    private final ForbiddenApi api;

    @Inject
    public InterceptorModelModuleTest(ForbiddenApi api) {
        this.api = api;
    }

    @Test(expectedExceptions = {ForbiddenException.class})
    public void testForbiddenApi() {
        api.mustBeForbidden();
    }
}