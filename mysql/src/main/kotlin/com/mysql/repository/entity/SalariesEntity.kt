package com.mysql.repository.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "salaries")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SalariesEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_no")
    var empNo: Int,

    @Column(name = "salary")
    var salary: Int? = null,

    @Column(name = "from_date")
    var fromDate: LocalDate? = null,

    @Column(name = "to_date")
    var toDate: LocalDate? = null,

//    @Column(name = "gender")
//    @Enumerated(EnumType.ORDINAL)
//    var gender: Gender? = null,

    //    @Enumerated(EnumType.ORDINAL)
    //    @Column(name = "gender")
    //    private Gender gender;
    @Transient
    @JsonProperty
    var pageSize: Int? = null,

    @Transient
    @JsonProperty
    var currentPage: Int? = null

) : Serializable