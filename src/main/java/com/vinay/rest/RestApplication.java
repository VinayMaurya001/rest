package com.vinay.rest;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
@ComponentScan(basePackages = { "com.vinay.rest" })
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	/*@Bean
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource resourceBundleMessageSource=new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename("messages");
		return resourceBundleMessageSource;
	}*/
	
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
}
