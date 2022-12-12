package com.springredis.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.springredis.dto.EmployeeSalary
import com.springredis.repository.SalariesRepository
import com.springredis.service.SalaryService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service


@Service
class SalaryServiceImpl : SalaryService {
    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var salariesRepository: SalariesRepository

    @Cacheable("employeeWithSalary")
    override fun getEmployeeWithSalary(salary: Int, gender: String): List<*> {
//        return salariesRepository.getEmployeeWithSalary(salary, gender)
        val dataProjection = salariesRepository.getEmployeeWithSalary(salary)
        return dataProjection.stream()
            .map{ s -> EmployeeSalary(s.emp_no, s.salary, s.full_name, s.gender) }
            .toList()
    }

}