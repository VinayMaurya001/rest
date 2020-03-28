package com.vinay.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
				.withUser("user2").password("password2").roles("USER").and()
				.withUser("admin1").password("admin2").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
			//.antMatchers("/person/**").hasRole("USER")
			.antMatchers("/students/**").hasRole("USER")
			//.antMatchers("/students/**").hasRole("ADMIN")
			.and().csrf().disable()
			.headers().frameOptions().disable();
	}
}
