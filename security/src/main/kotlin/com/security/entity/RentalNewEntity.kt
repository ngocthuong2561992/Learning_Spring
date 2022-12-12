package com.security.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.security.config.jwt.user.UserEntity
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "rental_new")
@EntityListeners(AuditingEntityListener::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
open class RentalNewEntity(
    @Id
    @SequenceGenerator(name = "rental_seq", sequenceName = "rental_seq", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_seq")
    @Column(name = "rental_id")
    open var rentalId: Int? = null,

    @Column(name = "rental_date")
    open var rentalDate: LocalDateTime? = null,

    @Column(name = "inventory_id")
    open var inventoryId: Int? = null,

    @Column(name = "customer_id")
    open var customerId: Int? = null,

    @Column(name = "return_date")
    open var returnDate: LocalDateTime? = null,

    @Column(name = "staff_id")
    open var staffId: Int? = null,

    @CreatedDate
    @Column(name = "created_at")
    open var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at")
    open var updatedAt: LocalDateTime? = null,

    @ManyToOne
    @CreatedBy
    @JoinColumn(name = "created_by")
    open var createdBy: UserEntity? = null,

    @ManyToOne
    @LastModifiedBy
    @JoinColumn(name = "updated_by")
    open var updatedBy: UserEntity? = null

)