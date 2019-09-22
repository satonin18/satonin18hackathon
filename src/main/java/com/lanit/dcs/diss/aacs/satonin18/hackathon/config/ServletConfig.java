package com.lanit.dcs.diss.aacs.satonin18.hackathon.config;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.config.DbConfig;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

public class ServletConfig
		extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(
				DbConfig.class, ValidatorConfig.class
		);
		servletContext.addListener(new ContextLoaderListener(rootContext));

		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
		servletAppContext.register(SpringMvcConfig.class);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter(
				"encoding-filterForm", new CharacterEncodingFilter());
		encodingFilter.setInitParameter("encoding", "UTF-8");
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns(null, true, "/*");
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {
				DbConfig.class, ValidatorConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[0];
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[0];
	}
}
