package com.nnk.springboot.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   	PasswordEncoder encoder = 
         PasswordEncoderFactories.createDelegatingPasswordEncoder();
   	auth
         .inMemoryAuthentication()
         .withUser("user")
         .password(encoder.encode("Test123@password"))
         .roles("USER")
         .and()
         .withUser("admin")
         .password(encoder.encode("Test123@password"))
         .roles("USER", "ADMIN");
   }

	/**
	 * Configuring web based security for specific http requests & urls. Defines the
	 * different authorizations on the different urls
	 * 
	 * @param http HttpSecurity
	 * @throws Exception if any problem occurs
	 */
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/admin/**", "/app/**", "/user/**").hasRole("ADMIN")
				.antMatchers("/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**")
				.hasAnyRole("ADMIN", "USER").antMatchers("/", "/api/**", "/css/**").permitAll().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/bidList/list").failureUrl("/login").permitAll().and().logout()
				.logoutUrl("/logout").permitAll().and().exceptionHandling().accessDeniedPage("/forbidden");
	}
}