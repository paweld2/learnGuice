package warsjava.guice.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.pmsoft.tutorial.guice.api.direct.ModelContract;
import warsjava.guice.servlet.service.ModelService;

import com.google.inject.Inject;

@Singleton
public class ModelAccessServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1897717577051090955L;

	private final ModelContract model;
	
	private final ModelService service;
	
	@Inject
	public ModelAccessServlet(ModelContract model,ModelService service) {
		super();
		this.model = model;
		this.service = service;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if( model == null) throw new RuntimeException("model is null");
		
		service.checkRequestStatus();
		
		PrintWriter out = resp.getWriter();
		out.println("Guice injected servlet");
		out.println("Model environment is " + model.getEnvironmentName());
	}
}
