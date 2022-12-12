package com.multidb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.multidb.repository.employee.dto.EmployeeInfo;
import com.multidb.repository.employee.entity.EmployeeEntity;
import com.multidb.repository.sakila.dto.MovieRentalInfo;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;

import java.util.List;

public interface ApiService {
    List<MovieRentalInfo> getRentalMovies(ModelMap params);
    List<EmployeeInfo> getEmployeeSalary(Integer amount);
    void saveEmployee();
    void saveSalary();
    void testSave();

    List<?> handleData() throws JsonProcessingException;
    Page<EmployeeEntity> getProductList(EmployeeEntity dto);
}
