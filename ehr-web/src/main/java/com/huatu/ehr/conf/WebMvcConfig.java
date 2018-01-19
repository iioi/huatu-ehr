package com.huatu.ehr.conf;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.huatu.ehr.web.Interceptor.SecurityInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.huatu.ehr.web")
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * 配置过滤静态资源
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
	}
	
	/**
	 * 简单视图映射
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/test").setViewName("hello");
	}
	
	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(securityInterceptor()).addPathPatterns("/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	/**
	 * 配置json转换
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(messageConverter());
		WebMvcConfigurer.super.configureMessageConverters(converters);
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter messageConverter() {
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
		messageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		return messageConverter;
	}
	
	/**
	 * 配置html视图解析器
	 */
	@Bean
	public ViewResolver ViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}
	
	@Bean
	public SecurityInterceptor securityInterceptor() {
		return new SecurityInterceptor();
	}
}
