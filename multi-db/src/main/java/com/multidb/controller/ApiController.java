package com.multidb.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multidb.repository.employee.EmployeeRepository;
import com.multidb.repository.employee.dto.EmployeeInfo;
import com.multidb.repository.employee.entity.EmployeeEntity;
import com.multidb.repository.mongoLocal.GroceryItemRepository;
import com.multidb.repository.mongoLocal.entity.GroceryItem;
import com.multidb.repository.sakila.dto.MovieRentalInfo;
import com.multidb.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static final Marker IMPORTANT = MarkerFactory.getMarker("IMPORTANT");

    @Autowired
    @Qualifier("customObjectMapper")
    private ObjectMapper mapper;

    @Autowired
    GroceryItemRepository groceryItemRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping(value = "/testSave")
    public void testSave() {
        apiService.saveEmployee();
    }

//    @PostMapping(value = "/groceries")
//    public <T> List<T> groceries() {
//        return (List<T>) groceryItemRepository.findAll();
//    }

    @PostMapping("/getProductData")
    JsonNode getProductData() throws JsonProcessingException {
        String jsonStr = "{\"ownerArray\":[{\"id\":1,\"value\":\"John Nash\"},{\"id\":2,\"value\":\"Leonhard Euler\"},{\"id\":3,\"value\":\"Alan Turing\"}],\"categoryArray\":[{\"id\":1,\"value\":\"Clothing\"},{\"id\":2,\"value\":\"Jewelery\"},{\"id\":3,\"value\":\"Accessory\"}]}";
        JsonNode jsonNode = mapper.readTree(jsonStr);
//        String json = "{\"id\": 49, \"name\": \"小林　利奈\", \"type\": 1}";
//        return mapper.readTree(json);
        LOGGER.info("This is a log message that is not important!");
        LOGGER.info(IMPORTANT, "This is a very important log message!");
        return jsonNode;
    }

    @GetMapping(value = "/getRentalMovies")
    public List<MovieRentalInfo> getRentalMovies(@RequestBody ModelMap params) {
        return apiService.getRentalMovies(params);
    }

    @GetMapping(value = "/getSalaryByAmount")
    public List<EmployeeInfo> getSalaryByAmount(@RequestBody ModelMap params) {
        return apiService.getEmployeeSalary((Integer) params.get("amount"));
    }

    @GetMapping(value = "/handleData")
    public List<?> handleData() throws JsonProcessingException {
        return apiService.handleData();
    }

    @PostMapping(value = "/getProductList")
    public Page<EmployeeEntity> getUserDetailsList(@RequestBody EmployeeEntity dto) {
        return apiService.getProductList(dto);
    }

}
