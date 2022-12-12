package com.h2database

import com.h2database.entity.UserEntity
import com.h2database.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class H2databaseApplication : CommandLineRunner {
    @Autowired
    lateinit var userRepository: UserRepository

    override fun run(vararg args: String?) {
        val user = UserEntity()
        user.username = "loda"
        user.password = "123456"
        userRepository.save(user)
    }
}

fun main(args: Array<String>) {
    runApplication<H2databaseApplication>(*args)
}
