package com.multidatasource.controller

import com.multidatasource.repository.employee.SalariesRepository
import com.multidatasource.repository.employee.dto.EmployeeInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController {

    @Autowired
    lateinit var salariesRepository: SalariesRepository

    @GetMapping("/getSalaryByAmount")
    fun getSalaryByAmount(@RequestBody params : ModelMap): List<Any?>? {
        val data = salariesRepository.getEmployeeSalary(params["amount"] as Int)
//        return salariesRepository.findAllById(listOf(253406))
//        val email = values["email"] ?: throw IllegalStateException("Email is missing!")
        return data
                .filter { s -> s.salary > 38623 }
//                .map { s -> s.toEmployeeView() }
                .map { s -> EmployeeView(name = s.fullName, salary = s.salary) }
    }

    private fun EmployeeInfo.toEmployeeView() = EmployeeView(
            name = "$fullName - $empNo",
            salary = salary
    )
    data class EmployeeView(
            val name: String,
            val salary: Int
    )
}
