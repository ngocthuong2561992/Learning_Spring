package com.graphqldemo.enity

import javax.persistence.*

@Entity
@Table(name = "`tutorial`")
class Tutorial (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column(name = "title", nullable = false)
    var title: String? = null,

    @Column(name = "description")
    var description: String? = null,

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, updatable = false)
    var author: Author? = null
)