package com.security.config.jwt.user

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
open class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    open var userId: Int? = null,

    @Column(nullable = false, unique = true)
    open var username: String? = null,

    @Column(nullable = false)
    open var password: String? = null,

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "role_name", nullable = false)
    @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
    open var roles: List<String>? = null
) : Serializable