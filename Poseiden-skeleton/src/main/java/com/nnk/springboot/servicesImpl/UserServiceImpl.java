package com.nnk.springboot.servicesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUserFromAuth(Authentication auth) {
		// TODO Auto-generated method stub
		return null;
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
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(int id) {
		userRepository.delete(findUserById(id));
	}
}