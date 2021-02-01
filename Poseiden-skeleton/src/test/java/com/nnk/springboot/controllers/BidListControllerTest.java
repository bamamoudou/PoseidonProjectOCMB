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
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.servicesImpl.BidListServiceImpl;

//@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest(BidListController.class)
//@AutoConfigureMockMvc
public class BidListControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webContext;

	@MockBean
	private BidListServiceImpl bidListService;

	private BidList bidList;
	private List<BidList> listOfBids;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		bidList = new BidList("My account", "type", 10.0);
		bidList.setBidListId(1);
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	public void findBidListByIdTest() throws Exception {
		when(bidListService.findBidListById(bidList.getBidListId())).thenReturn(bidList);
		mockMvc.perform(get("/bidList/list").param("id", "1")).andExpect(status().is2xxSuccessful());

	}

	@Test
	public void findBidListByListHomeTest() throws Exception {
		when(bidListService.findBidListByList()).thenReturn(listOfBids);
		mockMvc.perform(
				get("/bidList/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(bidList)))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void addBidListTest() throws Exception {
		when(bidListService.saveBidList(bidList)).thenReturn(bidList);
		mockMvc.perform(
				post("/bidList/add").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(bidList)))
				.andExpect(status().isCreated());
	}

	@Test
	public void updateBidListTest() throws JsonProcessingException, Exception {
		when(bidListService.updateBidList(bidList)).thenReturn(bidList);
		mockMvc.perform(
				put("/bidList/update").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(bidList)))
				.andExpect(status().is2xxSuccessful());

	}

	@Test
	public void deleteBidIfExistTest() throws Exception {
		mockMvc.perform(delete("/bidList/delete/" + 1)).andExpect(status().isOk());
	}
}
