package com.springaop.aspect.controller;


import com.springaop.aspect.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private TestService testService;

    @Autowired
    private Environment env;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/callDaoSuccess")
    public void callDaoSuccess(HttpServletRequest request) {
        System.out.println("PROXY (testService): " + testService.getClass().getName());
        testService.callDaoSuccess();
    }

    @GetMapping(value = "/callDaoFailed")
    public void callDaoFailed() {
        testService.callDaoFailed();
    }

    @GetMapping(value = "/callDaoTrackTime")
    public void callDaoTrackTime() {
        testService.callDaoTrackTime();
    }

}
