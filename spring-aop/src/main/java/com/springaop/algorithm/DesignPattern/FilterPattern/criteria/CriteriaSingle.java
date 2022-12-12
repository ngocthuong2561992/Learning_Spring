package com.springaop.algorithm.DesignPattern.FilterPattern.criteria;

import com.springaop.algorithm.DesignPattern.FilterPattern.Person;

import java.util.ArrayList;
import java.util.List;

public class CriteriaSingle implements Criteria {
	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> singlePersons = new ArrayList<Person>(); 
	      
		for (Person person : persons) {
			if(person.getMaritalStatus().equalsIgnoreCase("SINGLE")){
				singlePersons.add(person);
			}
		}
		return singlePersons;
	}
}
