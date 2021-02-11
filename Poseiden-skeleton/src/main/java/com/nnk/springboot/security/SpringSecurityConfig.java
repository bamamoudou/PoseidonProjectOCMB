package com.nnk.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * UserDetailsService to inject.
	 */
	private final UserDetailsService userDetailsService;

	/**
	 * Instantiates a new Poseidon security config.
	 *
	 * @param pUserDetailsService the UserDetailsService
	 */
	@Autowired
	public SpringSecurityConfig(@Qualifier("myUserDetailsService") final UserDetailsService myUserDetailsService) {
		this.userDetailsService = myUserDetailsService;
	}

	/**
	 * Allows building UserDetailsService based authentication.
	 * 
	 * @param auth AuthenticationManagerBuilder
	 * @throws Exception if an error occurs when building the UserDetailsService
	 *                   based authentication
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	/**
	 * Configuring web based security for specific http requests & urls. Defines the
	 * different authorizations on the different urls
	 * 
	 * @param http HttpSecurity
	 * @throws Exception if any problem occurs
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/admin/**", "/app/**", "/api/user/**")
				.hasAuthority("ADMIN")
				.antMatchers("/bidList/**", "/rating/**", "/ruleName/**", "/trade/**", "/curvePoint/**")
				.hasAnyAuthority("ADMIN", "USER").antMatchers("/api/**").authenticated().and().formLogin()
				.defaultSuccessUrl("/api/bidList/list", true).and().logout().logoutUrl("/app-logout")
				.logoutSuccessUrl("/login").and().exceptionHandling().accessDeniedPage("/app/error");
	}
}