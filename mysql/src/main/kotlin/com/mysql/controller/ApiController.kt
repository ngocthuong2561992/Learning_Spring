package com.mysql.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.mysql.service.ApiService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api"])
class ApiController {
    @Autowired
    lateinit var apiService: ApiService

    private val LOGGER = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    @GetMapping(value = ["/testSave"])
    fun testSave() {
        apiService.testSave()
    }

    @GetMapping("/handleData")
    fun handleData(): List<*> {
        return apiService.handleData()
    }

}