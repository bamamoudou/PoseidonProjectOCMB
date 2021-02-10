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

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserServiceImpl userService;

	private static final Logger LOGGER = LoggerFactory.getLogger(BidListController.class);

	@GetMapping("/list")
	public List<User> home() {
		return userService.findAllUsers();

	}

	@PostMapping("/add")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/findByUsername/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userService.findUserByUserName(username);
	}

	@PutMapping("/update")
	public User updateUser(@Valid @RequestBody User user) {
		return userService.updateUser(user);
	}

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