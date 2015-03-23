package com.greglturnquist.social.ecobee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("greg").password("turnquist").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin()
				.loginPage("/signin")
				.loginProcessingUrl("/signin/authenticate")
				.failureUrl("/signin?param.error=bad_credentials")
				.and()
			.logout()
				.logoutUrl("/signout")
				.deleteCookies("JSESSIONID")
				.and()
			.authorizeRequests()
				.antMatchers("/favicon.ico", "/auth/**", "/signin/**", "/signup/**").permitAll()
				.antMatchers("/**").authenticated()
				.and()
			.rememberMe();

	}
}
