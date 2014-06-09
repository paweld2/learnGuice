package eu.pmsoft.tutorial.guice.model;

import com.google.inject.Inject;

public class Comment {

	private final int id;
    private final String content;

	@Inject
	public Comment(int id, String content) {
		super();
		this.id = id;
        this.content = content;
    }

	public int getId() {
		return id;
	}

    public String getContent() {
        return content;
    }
}
