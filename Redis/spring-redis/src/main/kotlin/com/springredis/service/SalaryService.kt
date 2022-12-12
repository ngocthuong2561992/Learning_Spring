package com.springredis.service

interface SalaryService {
    fun getEmployeeWithSalary(salary: Int, gender: String): List<*>
}