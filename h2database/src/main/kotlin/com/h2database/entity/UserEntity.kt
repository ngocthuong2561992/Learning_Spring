package com.h2database.entity

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "`user`")
@JsonInclude(JsonInclude.Include.NON_NULL)
open class UserEntity(
    @Id
    @GeneratedValue
    open var id: Long? = null,

    @Column(nullable = false, unique = true)
    open var username: String? = null,
    open var password: String? = null
) : Serializable