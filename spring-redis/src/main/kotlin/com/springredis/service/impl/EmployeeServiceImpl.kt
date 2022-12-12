package com.springredis.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.springredis.enums.Gender
import com.springredis.repository.EmployeeRepository
import com.springredis.service.EmployeeService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional


@Service
class EmployeeServiceImpl : EmployeeService {
    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

}