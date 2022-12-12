package com.springredis.dto;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface EmployeeInfo {
    @Value("#{target.emp_no}")
    String getEmpNo();

    @Value("#{target.salary}")
    Integer getSalary();

    @Value("#{target.from_date}")
    LocalDate getFromDate();

    @Value("#{target.full_name}")
    String getFullName();

//    Job getJob();
//
//    interface Job {
//        Long getId();
//        String getJobName();
//    }
}
