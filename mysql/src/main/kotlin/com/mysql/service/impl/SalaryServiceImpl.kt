package com.mysql.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.mysql.repository.EmployeeRepository
import com.mysql.repository.SalariesRepository
import com.mysql.service.SalaryService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Service
class SalaryServiceImpl : SalaryService {
    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var salariesRepository: SalariesRepository

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Transactional
    override fun saveSalary(amount: Int) {
        val localDate = LocalDate.parse("2000-02-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val entity = salariesRepository.getSalary(253406, localDate)
        entity.salary = amount
        salariesRepository.save(entity)
        val a = 1 / 0
    }

}