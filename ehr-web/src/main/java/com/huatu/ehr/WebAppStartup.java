package com.huatu.ehr;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.ApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppStartup implements WebApplicationInitializer {

	private static final long MAX_FILE_UPLOAD_SIZE = 1024 * 1024 * 5; // 5 Mb

	private static final int FILE_SIZE_THRESHOLD = 1024 * 1024; // After 1Mb

	private static final long MAX_REQUEST_SIZE = -1L; // No request size limit

	private static WebApplicationContext webApplicationContext;

	/**
	 * 服务器启动调用此方法，可以在这里做配置
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// 注册Spring MVC的servlet
		this.addServlet(servletContext);
		// 注册spring character过滤器
		this.addFilter(servletContext);
		
		servletContext.getServletRegistration("jsp").addMapping("*.html");
	}

	/**
	 * 注册Spring servlet
	 */
	private void addServlet(ServletContext servletContext) {
		// 构建一个application context
		AnnotationConfigWebApplicationContext webContext = createWebContext();
		// 注册spring mvc的servlet
		Dynamic dynamic = servletContext.addServlet("spring-mvc", new DispatcherServlet(webContext));
		// 添加springMVC允许访问的controller后缀
		dynamic.addMapping("/");
		dynamic.setLoadOnStartup(1);
		dynamic.setMultipartConfig(
				new MultipartConfigElement(null, MAX_FILE_UPLOAD_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD));
	}

	/**
	 * 通过自定义的配置类来实例化一个 Web Application Context
	 */
	private AnnotationConfigWebApplicationContext createWebContext() {
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.scan("com.huatu.ehr.conf");
		WebAppStartup.webApplicationContext = webContext;
		return webContext;
	}

	/**
	 * 添加字符过滤器
	 */
	public void addFilter(ServletContext servletContext) {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8");
		javax.servlet.FilterRegistration.Dynamic dynamic = servletContext.addFilter("encodingFilter",
				characterEncodingFilter);
		dynamic.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
	}

	/**
	 * 获取到applicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return webApplicationContext;
	}
}
