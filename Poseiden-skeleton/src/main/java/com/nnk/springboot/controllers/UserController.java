package com.nnk.springboot.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.servicesImpl.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Class used to manage user")
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserServiceImpl userService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	/**
	 * Retrieve all the Users from database.
	 *
	 * @return a list of all the Users
	 */
	@ApiOperation(value = "Retrieve all the existing Users from database")
	@GetMapping("/list")
	public List<User> home() {
		LOGGER.debug("GET request sent from the UserController" + " to retrieve all the Users");
		return userService.findAllUsers();

	}

	/**
	 * Check data valid and save to db
	 * 
	 * @param user
	 * @return user added
	 */
	@ApiOperation(value = "Save a new User to database")
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		LOGGER.debug("POST request sent from the" + " UserController to save a new User");
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}

	/**
	 * Find User by username.
	 *
	 * @param username the username of the User to find
	 * @return a User if found
	 */
	@ApiOperation(value = "Retrieve a User by its username from database")
	@GetMapping("/findByUsername/{username}")
	public User getUserByUsername(@PathVariable String username) {
		LOGGER.debug("GET request sent from the UserController to" + " find a User by username {}", username);
		return userService.findUserByUserName(username);
	}

	/**
	 * Update an existing User.
	 * 
	 * @param user
	 * @return user updated
	 */
	@ApiOperation(value = "Update an existing User")
	@PutMapping("/update")
	public User updateUser(@Valid @RequestBody User user) {
		LOGGER.debug("PUT request sent from the" + " UserApiController to update User {}", user);
		return userService.updateUser(user);
	}

	/**
	 * Delete an existing User.
	 * 
	 * @param id
	 * @param response
	 */
	@ApiOperation(value = "Delete an existing User from database")
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable Integer id, HttpServletResponse response) {
		Optional<User> bidOptional = Optional.ofNullable(userService.findUserById(id));
		if (bidOptional.isPresent()) {
			LOGGER.info("delete, SUCCES");
			response.setStatus(200);
			userService.deleteUser(id);
		} else {
			LOGGER.error("Delete, ERROR");
		}
	}
}