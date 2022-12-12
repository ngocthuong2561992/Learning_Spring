package com.keycloak.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
    @GetMapping(value = "/getRentalMovies")
    public <T> List<T> getRentalMovies(@RequestParam("title") String title) {
        return null;
    }

}
