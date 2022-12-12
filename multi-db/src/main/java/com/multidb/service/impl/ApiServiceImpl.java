package com.multidb.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multidb.enums.Gender;
import com.multidb.repository.employee.EmployeeRepository;
import com.multidb.repository.employee.SalariesRepository;
import com.multidb.repository.employee.dto.EmployeeInfo;
import com.multidb.repository.employee.entity.EmployeeEntity;
import com.multidb.repository.employee.entity.SalariesEntity;
import com.multidb.repository.sakila.RentalRepository;
import com.multidb.repository.sakila.dto.MovieRentalInfo;
import com.multidb.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private SalariesRepository salariesRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    @Qualifier("customObjectMapper")
    private ObjectMapper mapper;

    @Override
    public List<MovieRentalInfo> getRentalMovies(ModelMap params) {
        return rentalRepository.getRentalMovies((String) params.get("title"));
    }

    @Override
    public List<EmployeeInfo> getEmployeeSalary(Integer amount) {
        return salariesRepository.getEmployeeSalary(amount);
    }

    @Override
    @Transactional(transactionManager = "employeeTransactionManager")
    public void saveEmployee() {
        EmployeeEntity entity = employeeRepository.findById(253406).get();
        entity.setGender(Gender.M);
        employeeRepository.save(entity);
        int a = 1/0;
    }

    @Override
    @Transactional
    public void saveSalary() {

    }

    @Override
    @Transactional
    public void testSave() {
        saveEmployee();
        saveSalary();
    }
    @Override
    public List<?> handleData() throws JsonProcessingException {
//        var salaryData = salariesRepository.findAll()
        List<SalariesEntity> salaryData = salariesRepository.getSalaryTest();

//        List<EmployeeEntity> employeeData = employeeRepository.findAll();

        List<EmployeeEntity> result = employeeRepository.getEmployeeTest();

//        var result = employeeData.take(2)

        Long start = System.currentTimeMillis();
        System.out.printf("start: $start ********************************");
        for (EmployeeEntity s : result) {
            List<SalariesEntity> list = salaryData.stream()
                    .filter(it -> it.getEmpNo() == s.getEmpNo())
                    .collect(Collectors.toList());
            System.out.printf(mapper.writeValueAsString(list));
//            s.setSalary();
        }

        Long end = System.currentTimeMillis();
        System.out.printf("""
                Total time: ${end - start}
        """);
        System.out.printf("end: $end ********************************");

        Map<Integer, List<SalariesEntity>> salaryMap = salaryData.stream()
                .collect(Collectors.groupingBy(SalariesEntity::getEmpNo));
        return result;
    }

    @Override
    public Page<EmployeeEntity> getProductList(EmployeeEntity dto) {
        Pageable pageable = PageRequest.of(dto.getCurrentPage(), dto.getPageSize());
        return employeeRepository.getProductList(dto, pageable);
    }

}
