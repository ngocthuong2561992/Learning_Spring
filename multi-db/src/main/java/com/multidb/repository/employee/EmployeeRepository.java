package com.multidb.repository.employee;

import com.multidb.repository.employee.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    @Query(nativeQuery = true, value = """
        SELECT *
        FROM employees A 
        WHERE A.emp_no in (10001,10002)
    """)
    List<EmployeeEntity> getEmployeeTest();

    @Query(nativeQuery = true, value = ""
            +   "SELECT A.* "
            +   "FROM employees A "
            +   "WHERE (:#{#params.first_name} IS NULL OR A.first_name LIKE %:#{#params.first_name}%) ",
            countQuery = ""
                    +   "SELECT COUNT(*) "
                    +   "FROM employees A "
                    +   "WHERE (:#{#params.first_name} IS NULL OR A.first_name LIKE %:#{#params.first_name}%) ")
    Page<EmployeeEntity> getProductList(@Param("params") EmployeeEntity params, Pageable pageable);

}