package com.jpa.service;

import com.jpa.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApiService {
    <T> List<T> getRentalMovies(String title);
    <T> T testJpaSave();
    <T> T handleTransactional();
    void handleTransactionalReplica();
    <T> T handleLargeData();
    Page<EmployeeEntity> getEmployeeList(EmployeeEntity dto);

    void importExcel(MultipartFile file) throws Exception;
}
