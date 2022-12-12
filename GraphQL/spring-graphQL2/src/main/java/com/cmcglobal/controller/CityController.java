package com.cmcglobal.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.cmcglobal.entity.CityEntity;
import com.cmcglobal.repository.CityRepository;


@Controller
//@RequestMapping(value = "/api")
public class CityController {
  @Autowired
  private CityRepository cityRepository;

  @QueryMapping
  public List<CityEntity> findCity(@Argument String city) {
      return cityRepository.findByCity(city);
  }
}

