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
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.servicesImpl.CurveServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(CurveController.class)
public class CurveControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webContext;

	@MockBean
	private CurveServiceImpl curveService;

	private CurvePoint curve;
	private List<CurvePoint> curves;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		curve = new CurvePoint(1, 10.0, 20.0);
		curve.setValue(25.0);
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	public void findCurPointByIdTest() throws Exception {
		when(curveService.findCurvePointById(1)).thenReturn(curve);
		mockMvc.perform(get("/curvePoint/ + 1")).andExpect(status().is2xxSuccessful());

	}

	@Test
	public void findCurveByListTest() throws Exception {
		when(curveService.findCurvePointByList()).thenReturn(curves);
		mockMvc.perform(
				get("/curvePoint/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(curve)))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void addCurvePointTest() throws Exception {
		when(curveService.saveCurvePoint(curve)).thenReturn(curve);
		mockMvc.perform(
				post("/curvePoint/add").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(curve)))
				.andExpect(status().isCreated());
	}

	@Test
	public void updateCurvePointTest() throws JsonProcessingException, Exception {
		when(curveService.updateCurvePoint(curve)).thenReturn(curve);
		mockMvc.perform(
				put("/curvePoint/update").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(curve)))
				.andExpect(status().is2xxSuccessful());

	}

	@Test
	public void deleteCurvePointTest() throws Exception {
		mockMvc.perform(delete("/curvePoint/delete/" + 1)).andExpect(status().isOk());
	}
}