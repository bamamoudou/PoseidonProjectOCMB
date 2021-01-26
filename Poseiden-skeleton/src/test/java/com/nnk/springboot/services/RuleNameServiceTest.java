package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.servicesImpl.RuleNameServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RuleNameServiceTest {

	@Mock
	private RuleNameRepository ruleNameRepository;

	@InjectMocks
	private RuleNameServiceImpl ruleNameService;

	private RuleName ruleName;

	private List<RuleName> listResult;

	@Before
	public void setUp() {
		ruleName = new RuleName(1, "Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
	}

	@Test
	public void saveRuleNameTest() {
		// arrange
		when(ruleNameService.saveRuleName(ruleName)).thenReturn(ruleName);

		// act
		RuleName result = ruleNameService.saveRuleName(ruleName);

		// assert
		assertThat(result.getId()).isEqualTo(1);

		verify(ruleNameRepository, times(1)).save(any(RuleName.class));
	}

	@Test
	public void findRuleNameByIdTest() {
		// arrange
		when(ruleNameRepository.findById(ruleName.getId())).thenReturn(Optional.of(ruleName));

		// Act
		RuleName result = ruleNameService.findRuleNameById(ruleName.getId());

		// Assert
		assertThat(result.getId()).isEqualTo(ruleName.getId());
	}

	@Test
	public void findRuleNameByListTest() {
		// arrange
		listResult = new ArrayList<>();
		listResult.add(new RuleName(1, "Rule Name", "Description", "Json", "Template", "SQL", "SQL Part"));
		when(ruleNameRepository.findAll()).thenReturn(listResult);

		// act
		List<RuleName> result = ruleNameService.findRuleNameByList();

		// assert
		assertThat(result.size() > 0).isTrue();
		verify(ruleNameRepository, times(1)).findAll();
	}

	@Test
	public void updateRatingTest() {
		// arrange
		ruleName.setName("Admin");
		;

		when(ruleNameService.updateRuleName(ruleName)).thenReturn(ruleName);

		// act
		RuleName result = ruleNameService.updateRuleName(ruleName);

		// assert
		assertThat(result.getName()).isEqualTo("Admin");

		verify(ruleNameRepository, times(1)).save(any(RuleName.class));
	}

	@Test
	public void deleteRatingByIdTest() {
		// arrange
		ruleName.setId(1);
		when(ruleNameRepository.findById(ruleName.getId())).thenReturn(Optional.of(ruleName));
		Integer id = ruleName.getId();

		// act
		ruleNameService.deleteRuleNameById(id);

		// assert
		verify(ruleNameRepository, times(1)).delete(any(RuleName.class));
	}
}