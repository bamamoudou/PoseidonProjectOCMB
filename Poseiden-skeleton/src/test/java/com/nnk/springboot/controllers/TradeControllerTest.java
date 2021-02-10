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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.servicesImpl.TradeServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(TradeController.class)
public class TradeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webContext;

	@MockBean
	private TradeServiceImpl tradeService;

	private Trade trade;
	private List<Trade> trades;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		trade = new Trade(1, "account", "type");
		trade.setBuyQuantity(19.0);
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	public void findTradeByIdTest() throws Exception {
		when(tradeService.findTradeById(1)).thenReturn(trade);
		mockMvc.perform(get("/api/trade/ + 1")).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void findTradeByListHomeTest() throws Exception {
		when(tradeService.findTradeBylist()).thenReturn(trades);
		mockMvc
				.perform(
						get("/api/trade/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(trade)))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void addTradeTest() throws Exception {
		when(tradeService.saveTrade(trade)).thenReturn(trade);
		mockMvc
				.perform(
						post("/api/trade/add").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(trade)))
				.andExpect(status().isCreated());
	}

	@Test
	public void updateTradeTest() throws Exception {
		when(tradeService.updateTrade(trade)).thenReturn(trade);
		mockMvc.perform(
				put("/api/trade/update").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(trade)))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void deleteTradeIfExistTest() throws Exception {
		mockMvc.perform(delete("/api/trade/delete/" + 1)).andExpect(status().isOk());
	}
}