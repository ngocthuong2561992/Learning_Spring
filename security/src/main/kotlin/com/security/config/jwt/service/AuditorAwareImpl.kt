package com.security.config.jwt.service

import com.security.config.jwt.user.UserEntity
import com.security.config.jwt.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*


class AuditorAwareImpl : AuditorAware<UserEntity> {
    @Autowired
    lateinit var userRepository: UserRepository

    override fun getCurrentAuditor(): Optional<UserEntity> {
        val username = SecurityContextHolder.getContext().authentication.name
        return Optional.ofNullable(userRepository.findByUsername(username))
    }
}