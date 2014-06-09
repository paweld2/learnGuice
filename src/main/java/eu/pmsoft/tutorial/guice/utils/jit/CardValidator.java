package eu.pmsoft.tutorial.guice.utils.jit;

import com.google.inject.ProvidedBy;

@ProvidedBy(CardValidatorProvider.class)
public interface CardValidator {

	public boolean validate();
}
