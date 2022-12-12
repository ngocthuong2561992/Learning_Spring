package com.security

import com.security.config.jwt.user.UserEntity
import com.security.config.jwt.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class SecurityApplication : CommandLineRunner {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    override fun run(vararg args: String?) {
//        var user = UserEntity()
//        user.username = "admin"
//        user.password = passwordEncoder.encode("1234")
//        user.roles = listOf("ROLE_ADMIN","ROLE_USER")
//        userRepository.save(user)
//
//        user = UserEntity()
//        user.username = "user"
//        user.password = passwordEncoder.encode("1234")
//        user.roles = listOf("ROLE_USER")
//        userRepository.save(user)
    }
}

fun main(args: Array<String>) {
    runApplication<SecurityApplication>(*args)
}
