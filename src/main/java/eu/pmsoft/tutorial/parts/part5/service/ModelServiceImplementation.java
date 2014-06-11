package eu.pmsoft.tutorial.parts.part5.service;

import javax.servlet.http.HttpServletRequest;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.pmsoft.tutorial.guice.api.direct.ModelContract;
import eu.pmsoft.tutorial.guice.model.User;

public class ModelServiceImplementation implements ModelService {

	private final ModelContract model;

	@Inject
	public ModelServiceImplementation(ModelContract model,Provider<HttpServletRequest> requestProvider,Provider<User> userProvider) {
		this.model = model;
		this.requestProvider = requestProvider;
		this.userProvider = userProvider;
	}
	
	private final Provider<HttpServletRequest> requestProvider;
	
	private final Provider<User> userProvider;

	@Override
	public boolean checkRequestStatus() {
		if( model == null) throw new RuntimeException("model not injected");
		HttpServletRequest request = requestProvider.get();
		if( request == null) throw new RuntimeException("request not injected");
		User currentUser = userProvider.get();
		if( currentUser == null) throw new RuntimeException("user not injected");
		System.out.println(currentUser.getUserName());
		return false;
	}

}
