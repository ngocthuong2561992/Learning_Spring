package com.h2database.controller

import com.h2database.entity.UserEntity
import com.h2database.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@RequestMapping(value = ["/api"])
class ApiController {
    @Autowired
    lateinit var userRepository: UserRepository

    private val logger = LoggerFactory.getLogger(ApiController::class.java)

    @GetMapping(value = ["/user"])
    fun user(): List<UserEntity?> {
        return userRepository.findAll()
    }

}