package com.springredis.repository

import com.springredis.repository.entity.SalariesEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.io.Serializable
import java.time.LocalDate

@Repository
interface SalariesRepository : JpaRepository<SalariesEntity, Int> {
    @Query(nativeQuery = true, value = """
        SELECT *
        FROM salaries A 
        WHERE A.emp_no = :empNo 
            AND A.from_date = :fromDate
    """)
    fun getSalary(@Param("empNo") empNo: Int,
                          @Param("fromDate") fromDate: LocalDate): SalariesEntity

    interface EmployeeInfo: Serializable {
        val emp_no: String
        val salary: Int
//        val from_date: LocalDate,
        val full_name: String
        val gender: String
    }

    @Query(nativeQuery = true, value = """
        SELECT A.emp_no, A.salary, A.from_date, CONCAT(B.first_name, ' ', B.last_name) AS full_name , b.gender
        FROM salaries A
            INNER JOIN employees B ON B.emp_no = A.emp_no
        WHERE A.salary < :salary
        ORDER BY A.salary
    """)
    fun getEmployeeWithSalary(
        @Param("salary") salary: Int,
       ): List<EmployeeInfo>

    @Query(nativeQuery = true, value = """
        SELECT *
        FROM salaries A 
        WHERE A.emp_no in (10001,10002)
    """)
    fun getSalaryTest(): List<SalariesEntity>
}