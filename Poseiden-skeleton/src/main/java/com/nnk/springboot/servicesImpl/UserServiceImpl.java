package com.nnk.springboot.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	/**
	 * Checks if User from Authentication matches logged in User
	 */
	@Override
	public User getUserFromAuth(Authentication auth) {
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			User user = findUserByUserName(userDetails.getUsername());
			return user;
		}
		return null;
	}

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findUserById(int id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			return user;
		}
		return null;
	}

	@Override
	public User findUserByUserName(String username) {
		return userRepository.findUserByUsername(username);
	}

	@Override
	public User createUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		User updatedUser = findUserById(user.getId());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		updatedUser.setPassword(encoder.encode(user.getPassword()));
		updatedUser.setFullname(user.getFullname());
		updatedUser.setUsername(user.getUsername());
		updatedUser.setRole(user.getRole());
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(int id) {
		userRepository.delete(findUserById(id));
	}
}