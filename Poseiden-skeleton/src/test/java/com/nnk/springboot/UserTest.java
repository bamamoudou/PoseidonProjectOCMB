package com.nnk.springboot;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	@Autowired
	private UserRepository userRepository;

	@Test
	public void userTest() {
		User user = new User("mamoudou", "Test123@password", "Mamoudou Ba", "User");

		// Save
		user = userRepository.save(user);
		Assert.assertNotNull(user.getId());
		Assert.assertTrue(user.getUsername().equals("mamoudou"));

		// Update
		user.setUsername("Username Update");
		user = userRepository.save(user);
		Assert.assertTrue(user.getUsername().equals("Username Update"));

		// Find
		List<User> listResult = userRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = user.getId();
		userRepository.delete(user);
		Optional<User> tradeList = userRepository.findById(id);
		Assert.assertFalse(tradeList.isPresent());
	}
}