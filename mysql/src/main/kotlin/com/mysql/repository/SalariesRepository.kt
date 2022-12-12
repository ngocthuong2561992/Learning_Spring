package com.mysql.repository

import com.mysql.repository.entity.SalariesEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
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
}