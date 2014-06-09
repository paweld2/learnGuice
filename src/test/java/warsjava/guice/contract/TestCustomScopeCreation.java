package warsjava.guice.contract;

import static org.testng.AssertJUnit.assertEquals;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import org.testng.annotations.Test;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.PrivateModule;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.ScopeAnnotation;

public class TestCustomScopeCreation {

	public static class ControlledScope implements Scope, ScopeController {

		private final ThreadLocal<User> bindedUser = new ThreadLocal<User>();

		@Override
		public void enterScope(User user) {
			bindedUser.set(user);
		}

		@Override
		public void exitScope() {
			bindedUser.remove();
		}

		@Override
		public <T> Provider<T> scope(Key<T> key, Provider<T> unscoped) {
			return new Provider<T>() {

				@SuppressWarnings("unchecked")
				@Override
				public T get() {
					return (T) bindedUser.get();
				}
			};
		}

	}

	@Target({ TYPE, METHOD })
	@Retention(RUNTIME)
	@ScopeAnnotation
	public static @interface ControlledScopeAnnotation {
	}

	@Test
	public void testControlledScopeConstruction() {
		Injector injector = Guice.createInjector(new PrivateModule() {

			@Override
			protected void configure() {
				ControlledScope scope = new ControlledScope();
				bindScope(ControlledScopeAnnotation.class, scope);
				bind(ScopeController.class).toInstance(scope);
				bind(User.class).in(ControlledScopeAnnotation.class);
				expose(ScopeController.class);
				expose(User.class);
			}
		});
		ScopeController controller = injector.getInstance(ScopeController.class);
		ScopedService service = injector.getInstance(ScopedService.class);
		User user = new User();
		user.name = "x";
		service.checkState(false);
		
		controller.enterScope(user);
		service.checkState(true);
		controller.exitScope();

		service.checkState(false);
		
		controller.enterScope(user);
		service.checkState(true);
		controller.exitScope();

	}

	public static class User {
		String name;
	}

	public static interface ScopeController {
		public void enterScope(User user);

		public void exitScope();
	}

	public static class ScopedService {
		
		private final Provider<User> userProvider;

		@Inject
		public ScopedService(Provider<User> userProvider) {
			this.userProvider = userProvider;
		}

		public void checkState(boolean haveUser) {
			User user = userProvider.get();
			if (haveUser) {
				if (user == null)
					throw new RuntimeException("I expect to have a user binded in scope");
				assertEquals("x", user.name);
			} else {
				if (user != null)
					throw new RuntimeException("User is binded in scope and should not be");
			}
		}

	}

}
