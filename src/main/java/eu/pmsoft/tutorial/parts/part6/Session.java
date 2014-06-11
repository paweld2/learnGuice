package eu.pmsoft.tutorial.parts.part6;

/**
 * User: paweld2
 * Date: 2014-06-11
 */
public class Session {
    private int id;

    public static Session withId(int id) {
        Session session = new Session();
        session.setId(id);
        return session;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
