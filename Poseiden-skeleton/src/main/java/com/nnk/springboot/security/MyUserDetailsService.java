package com.nnk.springboot.security;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	/**
	 * class logger.
	 */
	private static final Logger LOGGER = LogManager.getLogger(MyUserDetailsService.class);
	/**
	 * UserRepository to inject.
	 */
	private final UserRepository repository;

	/**
    * Instantiates a new User details service.
    *
    * @param userRepository the user repository
    */
   @Autowired
   public MyUserDetailsService(final UserRepository userRepository) {
       this.repository = userRepository;
   }

	/**
	 * This method allows loading users by their usernames from database. It is used
	 * by the security layer for authenticating users
	 * 
	 * @param username the user's username
	 * @return a new instance of the UserDetails or throws an exception if no
	 *         matching username found in db
	 */
	@Override
	public UserDetails loadUserByUsername(final String username) {
		LOGGER.debug("Loading user {}", username);
		Optional<User> user = repository.findByUsername(username);
		if (user.isPresent()) {
			LOGGER.info("User '{}' loaded successfully", username);
			return new MyUserDetails(user.get());
		} else {
			LOGGER.error("Failed to load user '{}'", username);
			throw new UsernameNotFoundException("Not found");
		}
	}
}