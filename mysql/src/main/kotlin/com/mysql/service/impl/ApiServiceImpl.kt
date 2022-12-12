package com.mysql.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.mysql.enums.Gender
import com.mysql.repository.EmployeeRepository
import com.mysql.repository.SalariesRepository
import com.mysql.repository.entity.EmployeeEntity
import com.mysql.service.ApiService
import com.mysql.service.EmployeeService
import com.mysql.service.SalaryService
import com.mysql.service.TitleService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class ApiServiceImpl : ApiService {
    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var salaryService: SalaryService

    @Autowired
    lateinit var employeeService: EmployeeService

    @Autowired
    lateinit var titleService: TitleService

    @Autowired
    lateinit var salariesRepository: SalariesRepository

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Transactional
    override fun testSave() {
        employeeService.saveEmployee(Gender.M)
        titleService.saveTitle("aaa")
        salaryService.saveSalary(40000)
    }

    override fun handleData(): List<*> {
        val salaryList = salariesRepository.findAll()

        val employeeData = employeeRepository.findAll()
        val result = employeeData.take(10).toMutableList()
        result.add(EmployeeEntity(empNo = 9911))

        val start = System.currentTimeMillis()
        println("start: $start ********************************")

        val salaryMap: Map<Int, Int> = salaryList
            .filter { it.salary != null }
            .groupBy({ it.empNo }, { it.salary!! })
            .mapValues { it.value.sumOf { it } }

//        var salaryTree: TreeMap<Int, List<SalariesEntity>> = TreeMap(salaryMap)

        for (emp in result) {
            emp.salary = salaryMap[emp.empNo]
        }

        val end = System.currentTimeMillis()
        println("Total time: ${end - start}")
        println("end: $end ********************************")
        return result
    }
}