package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.servicesImpl.UserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webContext;

	@MockBean
	private UserServiceImpl userService;

	private User user;
	private List<User> listOfUser;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		user = new User();
		user.setId(1);
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	public void findUserByUsernameTestTest() throws Exception {
		when(userService.findUserByUserName(user.getUsername())).thenReturn(user);
		mockMvc.perform(get("/user/ + username")).andExpect(status().is2xxSuccessful());

	}

	@Test
	public void findUserListHomeTest() throws Exception {
		when(userService.findAllUsers()).thenReturn(listOfUser);
		mockMvc
				.perform(get("/user/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)))
				.andExpect(status().is2xxSuccessful());
	}

	//@Test
	public void addUserTest() throws Exception {
		when(userService.createUser(user)).thenReturn(user);
		mockMvc
				.perform(post("/user/add").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)))
				.andExpect(status().isCreated());
	}

	//@Test
	public void updateUserTest() throws JsonProcessingException, Exception {
		when(userService.updateUser(user)).thenReturn(user);
		mockMvc
				.perform(
						put("/user/update").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void deleteUserIfExistTest() throws Exception {
		mockMvc.perform(delete("/user/delete/" + 1)).andExpect(status().isOk());
	}
}