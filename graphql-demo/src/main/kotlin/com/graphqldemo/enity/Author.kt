package com.graphqldemo.enity

import javax.persistence.*

@Entity
@Table(name = "`author`")
class Author (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String? = null,

    @Column(name = "age")
    var age: Int? = null
)