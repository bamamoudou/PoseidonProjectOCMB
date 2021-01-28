package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BidListControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private BidListService bidListService;

	@Autowired
	private WebApplicationContext webContext;
	
	
	
	private BidList bidList;
	
	@Before
	public void setup() {
		bidList = new BidList("My account", "type", 10.0);
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}
	
	@Test
	public void findBidListByIdTest() throws Exception {
		mockMvc.perform(get("/bidList/list").param("id", "1")).andExpect(status().is2xxSuccessful());
		
	}
	
	@Test
	public void findBidListByListHome() throws Exception {
		mockMvc.perform(get("/bidList/list")).andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void deleteBidTest() throws Exception {
		mockMvc.perform(delete("/bidList/delete")).andExpect(status().is4xxClientError());
	}

}
