package com.replicadb.controller;


import com.replicadb.service.ActorService;
import com.replicadb.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @Autowired
    private ActorService actorService;

    @GetMapping(value = "/getSlave")
    public <T> T getSlave() {
        return apiService.getSlave();
    }

    @GetMapping(value = "/saveMaster")
    public void saveMaster() throws Exception {
        apiService.saveMaster();
    }

}
