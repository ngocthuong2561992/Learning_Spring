package com.multidatasource.repository.employee

import com.multidatasource.repository.employee.dto.EmployeeInfo
import org.springframework.data.jpa.repository.JpaRepository
import com.multidatasource.repository.employee.entity.SalariesEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SalariesRepository : JpaRepository<SalariesEntity?, Int?> {
    @Query(nativeQuery = true, value = """
        SELECT A.emp_no, A.salary, A.from_date, CONCAT(B.first_name, ' ', B.last_name) AS full_name 
        FROM salaries A 
           INNER JOIN employees B ON B.emp_no = A.emp_no 
        WHERE A.salary < :amount 
        ORDER BY A.salary
    """)
    fun getEmployeeSalary(@Param("amount") amount: Int): List<EmployeeInfo>
}