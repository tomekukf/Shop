package com.tomek.domek.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

	// private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	// "classpath:/static/", "classpath:/static/css/",
	// "classpath:/static/js/","classpath:/photos/",
	// "classpath:/META-INF/resources/",
	// "classpath:/resources/","classpath:/META-INF/resources/webjars/" };
	//
	// @Override
	// public void addResourceHandlers(ResourceHandlerRegistry registry) {
	//
	// if (!registry.hasMappingForPattern("/webjars/**")) {
	// registry.addResourceHandler("/webjars/**").addResourceLocations(
	// "classpath:/META-INF/resources/webjars/");
	// }
	// if (!registry.hasMappingForPattern("/static/**")
	// && !registry.hasMappingForPattern("/photos/**")) {
	// registry.addResourceHandler("/static/**","/photos/**","/META-INF/resources/**","/static/**","/META-INF/resources/webjars/**").addResourceLocations(
	// CLASSPATH_RESOURCE_LOCATIONS);
	//
	// }
	// if (!registry.hasMappingForPattern("/**")) {
	// registry.addResourceHandler("/**").addResourceLocations(
	// CLASSPATH_RESOURCE_LOCATIONS);
	// }
	// }

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/webjars/",
			"classpath:/resources/", "classpath:/static/", "classpath:/public/","classpath:/photos/" };

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**","/webjars/**","/css/**","/photos/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}
}
