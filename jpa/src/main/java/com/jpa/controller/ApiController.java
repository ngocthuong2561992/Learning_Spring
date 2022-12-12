package com.jpa.controller;


import com.jpa.dao.RentalDao;
import com.jpa.entity.EmployeeEntity;
import com.jpa.repository.RentalRepository;
import com.jpa.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @Autowired
    private RentalDao rentalDao;

    @Autowired
    private RentalRepository rentalRepository;

    @GetMapping(value = "/getRentalMovies")
    public <T> List<T> getRentalMovies(@RequestParam("title") String title) {
        return apiService.getRentalMovies(title);
    }

    @GetMapping(value = "/testJpaSave")
    public <T> T testJpaSave() {
        return apiService.testJpaSave();
    }

    @GetMapping(value = "/handleTransactional")
    public <T> T handleTransactional() throws InterruptedException {
         return apiService.handleTransactional();
//        apiService.handleTransactionalReplica();
    }

    @GetMapping(value = "/handleLargeData")
    public <T> T handleLargeData() {
        return apiService.handleLargeData();
    }

    @GetMapping(value = "/getEmployeePaging")
    public Page<EmployeeEntity> getEmployeeList(@RequestBody EmployeeEntity dto) {
        return apiService.getEmployeeList(dto);
    }

    @GetMapping(value = "/importLargeExcel")
    public void importLargeExcel(@RequestParam("file") MultipartFile file) throws Exception {
        apiService.importExcel(file);
    }

    @GetMapping(value = "/getCommonTableExpression")
    public <T> T getCommonTableExpression() {
        return (T) rentalDao.getCommonTableExpression();
    }

    @GetMapping(value = "/mapRowToColumn")
    public <T> T mapRowToColumn() {
        return (T) rentalDao.mapRowToColumn();
    }

    @GetMapping(value = "/getPartition")
    public <T> T getPartition() {
        return (T) rentalDao.getPartition();
    }

    @GetMapping(value = "/windowFunctions")
    public <T> T windowFunctions() {
        return (T) rentalRepository.windowFunctions();
    }

}
