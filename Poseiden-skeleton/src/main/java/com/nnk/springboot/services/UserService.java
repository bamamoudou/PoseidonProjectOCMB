package com.nnk.springboot.services;

import org.springframework.security.core.Authentication;

import com.nnk.springboot.domain.User;

public interface UserService {

	public User getUserFromAuth(Authentication auth);

	public User findUserById(int id);

	public User findUserByUserName(String username);

	public User createUser(User user);
	
	public User updateUser(User user);
	
	public void deleteUser(int id);
}