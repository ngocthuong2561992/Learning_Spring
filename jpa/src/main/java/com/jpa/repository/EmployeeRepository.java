package com.jpa.repository;

import com.jpa.entity.EmployeeEntity;
import com.jpa.entity.SalariesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    EmployeeEntity findByEmpNo(Integer empNo);

    @Query(nativeQuery = true, value = """
            SELECT A.*
            FROM employees A
            WHERE (:#{#params.firstName} IS NULL OR A.first_name LIKE '%'||:#{#params.firstName}||'%') """,
        countQuery = """
            SELECT COUNT(*)
            FROM employees A 
            WHERE (:#{#params.firstName} IS NULL OR A.first_name LIKE '%'||:#{#params.firstName}||'%') """)
    Page<EmployeeEntity> getEmployeeList(@Param("params") EmployeeEntity params, Pageable pageable);

}
