package eu.pmsoft.tutorial.parts.part6;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;

/**
 * User: paweld2
 * Date: 2014-06-11
 */
public class ControlledScope implements Scope, ScopeController {

    private final ThreadLocal<Session> bindedSession = new ThreadLocal<Session>();

    @Override
    public void enterScope(Session session) {
        bindedSession.set(session);
    }

    @Override
    public void exitScope() {
        bindedSession.remove();
    }

    @Override
    public <T> Provider<T> scope(Key<T> key, Provider<T> unscoped) {
        return new Provider<T>() {

            @SuppressWarnings("unchecked")
            @Override
            public T get() {
                return (T) bindedSession.get();
            }
        };
    }

}
