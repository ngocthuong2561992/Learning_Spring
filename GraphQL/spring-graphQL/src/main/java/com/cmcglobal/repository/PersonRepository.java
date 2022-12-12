package com.cmcglobal.repository;

import org.springframework.data.repository.CrudRepository;

import com.cmcglobal.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{

	Person findByEmail(String email);
}