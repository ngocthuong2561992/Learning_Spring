package com.mysql.repository.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "titles")
@JsonInclude(JsonInclude.Include.NON_NULL)
open class TitleEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_no")
    open var empNo: Int? = 0,

    @Column(name = "title")
    open var title: String? = null,

    @Column(name = "from_date")
    open var fromDate: LocalDate? = null,

    @Column(name = "to_date")
    open var toDate: LocalDate? = null,

) : Serializable