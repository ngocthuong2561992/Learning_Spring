package com.security.config.jwt.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Repository
interface UserRepository : JpaRepository<UserEntity?, Long?> {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun findByUsername(username: String): UserEntity
}