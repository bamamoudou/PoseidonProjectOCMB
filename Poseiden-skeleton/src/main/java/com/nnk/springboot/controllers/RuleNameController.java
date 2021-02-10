package com.nnk.springboot.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.servicesImpl.RuleNameServiceImpl;

@RestController
@RequestMapping("/api/ruleName")
public class RuleNameController {
	/**
	 * Inject RuleName service
	 */
	@Autowired
	private RuleNameServiceImpl ruleNameService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RuleNameController.class);

	/**
	 * find all RuleName
	 * 
	 * @return List of ruleName
	 */
	@GetMapping("/list")
	public List<RuleName> home() {
		return ruleNameService.findRuleNameByList();
	}

	/**
	 * Check data valid and save to db
	 * 
	 * @param ruleName
	 * @return rule name added
	 */
	@PostMapping("/add")
	public ResponseEntity<RuleName> addRule(@Valid @RequestBody RuleName ruleName) {
		return new ResponseEntity<RuleName>(ruleNameService.saveRuleName(ruleName), HttpStatus.CREATED);
	}

	/**
	 * Check required fields, if valid call service to update RuleName
	 * 
	 * @param ruleName
	 * @return rule name updated
	 */
	@PutMapping("/update")
	public RuleName updateRuleName(@Valid @RequestBody RuleName ruleName) {
		return ruleNameService.updateRuleName(ruleName);
	}

	/**
	 * Find RuleName by Id and delete the RuleName
	 * 
	 * @param id
	 */
	@DeleteMapping("/delete/{id}")
	public void deleteRuleName(@PathVariable Integer id, HttpServletResponse response) {
		Optional<RuleName> bidOptional = Optional.ofNullable(ruleNameService.findRuleNameById(id));
		if (bidOptional.isPresent()) {
			LOGGER.info("delete, SUCCES");
			response.setStatus(200);
			ruleNameService.deleteRuleNameById(id);
		} else {
			LOGGER.error("Delete, ERROR");
		}
	}
}