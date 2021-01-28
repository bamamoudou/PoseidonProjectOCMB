package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.RuleName;

/**
 * RuleNameService CRUD manager
 */
public interface RuleNameService {
	/**
	 * save the ruleName and return instance save
	 * 
	 * @param ruleName
	 * @return
	 */
	public RuleName saveRuleName(RuleName ruleName);

	/**
	 * return ruleName from id
	 * 
	 * @param id
	 * @return
	 */
	public RuleName findRuleNameById(int id);
	
	public RuleName findRuleNameByName(String ruleName);

	/**
	 * return list ruleName
	 * 
	 * @return
	 */
	public List<RuleName> findRuleNameByList();

	/**
	 * Update Rule Name
	 * 
	 * @param ruleName
	 * @return
	 */
	public RuleName updateRuleName(RuleName ruleName);

	/**
	 * delete ruleName from id
	 * 
	 * @param id
	 * @return
	 */
	public void deleteRuleNameById(int id);
}