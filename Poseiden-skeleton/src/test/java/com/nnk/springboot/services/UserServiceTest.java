package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.servicesImpl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;
	private User user;

	@Before
	public void setup() {
		user = new User();
		user.setId(1);
		user.setUsername("mamoudou");
		user.setPassword("Test123@password");
		user.setFullname("Mamoudou Ba");
		user.setRole("User");
	}

	@Test
	public void findUserByIdTest() {
		// arrange
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

		// act
		User result = userService.findUserById(1);

		// assert
		assertThat(result).isEqualTo(user);

	}

	@Test
	public void findUserByUserNameTest() {
		// arrange
		when(userRepository.findUserByUsername(user.getUsername())).thenReturn(user);

		// act
		User result = userService.findUserByUserName("mamoudou");

		// assert
		assertThat(result).isEqualTo(user);
	}

	@Test
	public void createUserTest() {
		// arrange
		when(userRepository.save(any())).thenReturn(user);

		// act
		User result = userService.createUser(user);

		// assert
		assertThat(result).isEqualTo(user);
		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	public void updateUser() {
		// arrange
		user.setUsername("doro");
		when(userRepository.save(any())).thenReturn(user);

		// act
		User result = userService.updateUser(user);

		// assert
		assertThat(result.getUsername()).isEqualTo("doro");
		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	public void deleteUser() {
		// arrange
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		Integer id = user.getId();

		// act
		userService.deleteUser(id);

		// assert
		verify(userRepository, times(1)).delete(any(User.class));
	}
}