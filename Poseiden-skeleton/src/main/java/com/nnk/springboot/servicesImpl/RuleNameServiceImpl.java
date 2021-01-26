package com.nnk.springboot.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;

@Service
public class RuleNameServiceImpl implements RuleNameService {
	@Autowired
	RuleNameRepository ruleNameRepository;

	@Override
	public RuleName saveRuleName(RuleName ruleName) {
		return ruleNameRepository.save(ruleName);
	}

	@Override
	public RuleName findRuleNameById(int id) {
		Optional<RuleName> ruleName = ruleNameRepository.findById(id);
		if (ruleName == null) {
			return null;
		}
		return ruleName.get();
	}

	@Override
	public List<RuleName> findRuleNameByList() {
		return ruleNameRepository.findAll();
	}

	@Override
	public RuleName updateRuleName(RuleName ruleName) {
		return ruleNameRepository.save(ruleName);
	}

	@Override
	public void deleteRuleNameById(int id) {
		ruleNameRepository.delete(findRuleNameById(id));
	}
}