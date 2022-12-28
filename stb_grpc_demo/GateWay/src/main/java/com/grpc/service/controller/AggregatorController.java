package com.grpc.service.controller;

import com.grpc.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class AggregatorController {

    @Autowired
    private UserService userService;

}
