package com.nnk.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nnk.springboot.constant.ConstantNumbers;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private MyUserDetailsService userDetailsService;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/user/**").hasAuthority("ADMIN")
//				.antMatchers("/bidList/**", "/rating/**", "/ruleName/**", "/trade/**", "/curvePoint/**")
//				.hasAnyAuthority("ADMIN", "USER").antMatchers("/**").authenticated().and().formLogin()
//				.defaultSuccessUrl("/bidList/list", true).and().logout().logoutUrl("/app-logout").logoutSuccessUrl("/login")
//				.and().exceptionHandling().accessDeniedPage("/app/error");
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("Test123@Password")).roles("ADMIN")
//				.and().withUser("user").password(encoder().encode("Test123@Password")).roles("USER");
////		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//	}
//
//   @Bean
//   public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder();
//   }
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//	}

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
//	@Override
//	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
//	}
	
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
				.loginPage("/login").defaultSuccessUrl("/api/bidList/list").failureUrl("/login").permitAll().and().logout()
				.logoutUrl("/logout").permitAll().and().exceptionHandling().accessDeniedPage("/forbidden");
	}

	/**
	 * Password encoder bean. Used to encode users' passwords
	 *
	 * @return a new instance of the BcryptPasswordEncoder
	 */
//	@Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder(ConstantNumbers.FIFTEEN);
//	}
}