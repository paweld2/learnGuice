package eu.pmsoft.tutorial.guice.utils.jit;

import com.google.inject.Provider;

public class CardValidatorProvider implements Provider<CardValidator>{

	@Override
	public CardValidator get() {
		return new CardValidator() {
			@Override
			public boolean validate() {
				return true;
			}
		};
	}

}
