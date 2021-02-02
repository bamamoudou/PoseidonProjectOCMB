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
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.servicesImpl.RuleNameServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(RuleNameController.class)
public class RuleNameControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webContext;

	@MockBean
	private RuleNameServiceImpl ruleNameService;

	private RuleName ruleName;
	private List<RuleName> ruleList;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		ruleName = new RuleName(1, "name", "description", "json", "template", "sqlStr", "sqlPart");
		ruleName.setName("rule name");
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	public void findRuleByListTest() throws Exception {
		when(ruleNameService.findRuleNameByList()).thenReturn(ruleList);
		mockMvc.perform(
				get("/ruleName/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ruleName)))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void addRuleTest() throws Exception {
		when(ruleNameService.saveRuleName(ruleName)).thenReturn(ruleName);
		mockMvc.perform(
				post("/ruleName/add").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ruleName)))
				.andExpect(status().isCreated());
	}

	@Test
	public void updateRuleTest() throws JsonProcessingException, Exception {
		when(ruleNameService.updateRuleName(ruleName)).thenReturn(ruleName);
		mockMvc.perform(put("/ruleName/update").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(ruleName))).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void deleteRuleTest() throws Exception {
		mockMvc.perform(delete("/ruleName/delete/" + 1)).andExpect(status().isOk());
	}
}