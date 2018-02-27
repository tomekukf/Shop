package com.tomek.domek.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {



	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/webjars/",
			"classpath:/resources/", "classpath:/static/", "classpath:/public/","classpath:/photos/","classpath:/static/css/allStyles/","classpath:/static/css/materia/" };

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**","/webjars/**","/css/**","/photos/**","/css/allStyles/**","/css/materia/**","/static/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}
	
	
//	@Bean
//	public UserDetailsService userDetailsService() throws Exception {
//	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//	    manager.createUser(User.withUsername("user").password("user").roles("USER").build());
//	    return manager;
//	}
}
