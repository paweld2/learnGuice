package eu.pmsoft.tutorial.parts.part6;

import com.google.inject.PrivateModule;

/**
 * User: paweld2
 * Date: 2014-06-11
 */
public class ScopedModule extends PrivateModule {
    @Override
    protected void configure() {

        ControlledScope scope = new ControlledScope();
        bindScope(ControlledScopeAnnotation.class, scope);
        bind(ScopeController.class).toInstance(scope);
        bind(Session.class).in(ControlledScopeAnnotation.class);
        expose(ScopeController.class);
        expose(Session.class);
    }
}
