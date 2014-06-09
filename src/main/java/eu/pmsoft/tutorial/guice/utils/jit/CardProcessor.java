package eu.pmsoft.tutorial.guice.utils.jit;

import com.google.inject.ImplementedBy;

@ImplementedBy(CardProcessorImpl.class)
public interface CardProcessor {

	public boolean processCard();
}
