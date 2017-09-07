package com.challenge.orderManager.config;

import org.aeonbits.owner.ConfigFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.challenge.orderManager")
@EnableJpaRepositories(basePackages = "com.challenge.orderManager.repositories")
@EntityScan(basePackages = "com.challenge.orderManager.entities")
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(Application.class);
//	}
//
//	public static void main(String[] args) {
//		SpringApplication.run( Application.class, args );
//	}
//
//	@Bean
//	public EnvironmentConfig buildEnvironmentConfig() {
//		return ConfigFactory.create( EnvironmentConfig.class, System.getenv() );
//	}
	
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/rest/*");

		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}