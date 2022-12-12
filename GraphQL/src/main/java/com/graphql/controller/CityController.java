package com.graphql.controller;


import com.graphql.entity.CityEntity;
import com.graphql.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
