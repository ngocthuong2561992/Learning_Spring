package com.itext.dto;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate

interface EmployeeInfo {
    @Value("#{target.emp_no}")
    fun getEmpNo() : String

    @Value("#{target.salary}")
    fun getSalary() : Int

    @Value("#{target.from_date}")
    fun getFromDate() : LocalDate

    @Value("#{target.full_name}")
    fun getFullName() : String

}
