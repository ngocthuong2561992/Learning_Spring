package com.springredis.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.math.BigDecimal
import java.math.BigInteger

data class EmployeeSalary (
    var emp_no: String?= null,
    var salary: Int?= null,
    var full_name: String?= null,
    var gender: String?= null
): Serializable