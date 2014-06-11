package eu.pmsoft.tutorial.parts.part6;

import com.google.inject.Inject;
import com.google.inject.Provider;


/**
 * User: paweld2
 * Date: 2014-06-11
 */
public class ScopedService {
    private final Provider<Session> userProvider;

    @Inject
    public ScopedService(Provider<Session> sessionProvider) {
        this.userProvider = sessionProvider;
    }

    public boolean checkThatIsEmpty() {
        return checkState(false,0);
    }
    public boolean checkSessionExists(int sessionID) {
        return checkState(true,sessionID);
    }
    private boolean checkState(boolean haveUser, int expectedId) {
        Session user = userProvider.get();
        if (haveUser) {
            if (user == null) {
                return false;
            } else {
                return expectedId== user.getId();
            }
        } else {
            if (user == null) {
                return true;
            } else {
                return false;
            }
        }
    }
}
