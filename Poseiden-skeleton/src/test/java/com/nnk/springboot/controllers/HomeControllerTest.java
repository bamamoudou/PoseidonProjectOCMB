package com.nnk.springboot.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nnk.springboot.services.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webContext;

	@MockBean
	private UserService userService;

	@Before
	public void setup() {
		// Init MockMvc Object and build
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	public void homeIfValidUserAndStatusIsSuccessfulTest() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().is2xxSuccessful());
	}

	@Test
	// @WithUserDetails("adminUser")
	public void adminHomeIfValidAdminAndStatusIsRedirection() throws Exception {
		mockMvc.perform(get("/admin/home")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/bidList/list"));
	}
}