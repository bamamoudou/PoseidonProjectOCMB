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
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.servicesImpl.RatingServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(RatingController.class)
public class RatingControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webContext;

	@MockBean
	private RatingServiceImpl ratingService;

	private Rating rating;
	private List<Rating> ratingList;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		rating = new Rating(1, "Rating", "sandPRatin", "fitchRating", 10);
		rating.setSandPRating("PRating");
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	public void findRatingByIdTest() throws Exception {
		when(ratingService.findRatingById(1)).thenReturn(rating);
		mockMvc.perform(get("/rating/ + 1")).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void findRatingByListTest() throws Exception {
		when(ratingService.findRatingByList()).thenReturn(ratingList);
		mockMvc.perform(
				get("/rating/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(rating)))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void addRatingTest() throws Exception {
		when(ratingService.saveRating(rating)).thenReturn(rating);
		mockMvc.perform(
				post("/rating/add").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(rating)))
				.andExpect(status().isCreated());
	}

	@Test
	public void updateRatingTest() throws JsonProcessingException, Exception {
		when(ratingService.updateRating(rating)).thenReturn(rating);
		mockMvc.perform(
				put("/rating/update").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(rating)))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void deleteRatingTest() throws Exception {
		mockMvc.perform(delete("/rating/delete/" + 1)).andExpect(status().isOk());
	}
}