package com.learnjava.custom.rule.engine;

/**
 * Defines rule. 
 * 
 * @author
 *
 */
public interface Rule {
	
	/**
	 * Returns the name of the rule.
	 * 
	 * @return
	 */
	String getName();
	
	/**
	 * Returns the rule condition.
	 * 
	 * @return
	 */
	RuleCondition getRuleCondition();

}
