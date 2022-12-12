package com.springaop.algorithm.DesignPattern.FilterPattern.criteria;

import com.springaop.algorithm.DesignPattern.FilterPattern.Person;

import java.util.List;

public interface Criteria {
	public List<Person> meetCriteria(List<Person> persons);
}
