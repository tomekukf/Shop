package com.tomek.domek.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
	

	    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	            "classpath:/static/", "classpath:/static/css/", "classpath:/static/js/","classpath:/photos/" };

	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        if (!registry.hasMappingForPattern("/static/**")
	        		 && !registry.hasMappingForPattern("/photos/**")) {
	            registry.addResourceHandler("/static/**","/photos/**").addResourceLocations(
	                    CLASSPATH_RESOURCE_LOCATIONS);
	        }
	    }
	}

