package com.multidatasource.repository.employee.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "salaries")
@JsonInclude(JsonInclude.Include.NON_NULL)
open class SalariesEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_no")
    open var empNo: Int? = 0,

    @Column(name = "salary")
    open var salary: Int? = null,

    @Column(name = "from_date")
    open var fromDate: LocalDate? = null,

    @Column(name = "to_date")
    open var toDate: LocalDate? = null,

//    @Column(name = "gender")
//    @Enumerated(EnumType.ORDINAL)
//    var gender: Gender? = null,

    //    @Enumerated(EnumType.ORDINAL)
    //    @Column(name = "gender")
    //    private Gender gender;
    @Transient
    @JsonProperty
    open var pageSize: Int? = null,

    @Transient
    @JsonProperty
    open var currentPage: Int? = null

) : Serializable