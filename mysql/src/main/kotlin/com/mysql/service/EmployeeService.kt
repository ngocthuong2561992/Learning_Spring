package com.mysql.service

import com.mysql.enums.Gender

interface EmployeeService {
    fun saveEmployee(gender: Gender)
}