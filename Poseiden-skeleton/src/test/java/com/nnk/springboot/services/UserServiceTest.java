package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

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
	public void FindUserFromAuthIfuserNotLoggedInAndNullReturned() {
		// arrange
		AnonymousAuthenticationToken authentication = mock(AnonymousAuthenticationToken.class);
		SecurityContext securityContext = mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);

		// act
		User result = userService.getUserFromAuth(authentication);

		// assert
		assertNull(result);
	}

	@Test
	public void findAll_userExists_usersReturned() {
		// arrange
		List<User> users = new ArrayList<>();
		users.add(user);

		when(userRepository.findAll()).thenReturn(users);

		// act
		List<User> listResult = userService.findAllUsers();

		// assert
		assertTrue(listResult.size() > 0);
	}

	@Test
	public void findAll_userDoesNotExists_noUsersReturned() {
		// arrange

		// act
		List<User> listResult = userService.findAllUsers();

		// assert
		assertTrue(listResult.size() == 0);
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
		when(userRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(user));

		// act
		userService.updateUser(user);

		// assert
		verify(userRepository, times(1)).save(any(User.class));
		assertThat(user.getUsername()).isEqualTo("doro");
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