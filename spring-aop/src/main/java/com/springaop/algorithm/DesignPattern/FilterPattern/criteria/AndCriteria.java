package com.springaop.algorithm.DesignPattern.FilterPattern.criteria;

import com.springaop.algorithm.DesignPattern.FilterPattern.Person;

import java.util.List;

public class AndCriteria implements Criteria {
	private Criteria criteria;
	private Criteria otherCriteria;

	public AndCriteria(Criteria criteria, Criteria otherCriteria) {
		this.criteria = criteria;
	      this.otherCriteria = otherCriteria; 
	}

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
	   
		List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);		
		return otherCriteria.meetCriteria(firstCriteriaPersons);
	}
}
