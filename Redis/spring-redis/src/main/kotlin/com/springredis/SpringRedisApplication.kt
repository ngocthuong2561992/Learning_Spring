package com.springredis

import com.springredis.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching


@SpringBootApplication
class SpringRedisApplication: CommandLineRunner {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var userService: UserService

    override fun run(vararg args: String?) {
//        logger.info("------------------ demo @Cacheable --------------------");
//        logger.info("find user with id = 1: {}", userService.findUserById(1));
//        logger.info("find user with id = 1: {}", userService.findUserById(1));
//        logger.info("find user with id = 2: {}", userService.findUserById(2));
//        logger.info("find user with id = 2: {}", userService.findUserById(2));
//        logger.info("------------------ demo @CacheEvict --------------------");
//        userService.clearCache();
//        logger.info("find user with id = 1: {}", userService.findUserById(1));
//        logger.info("find user with id = 2: {}", userService.findUserById(2));
//        logger.info("------------------ demo @CachePut --------------------");
//        logger.info("reload and find user with id = 1: {}", userService.reloadAndFindUserById(1));
//        logger.info("find user with id = 1: {}", userService.findUserById(1));
//        logger.info("find user with id = 2: {}", userService.findUserById(2));
    }
}

fun main(args: Array<String>) {
    runApplication<SpringRedisApplication>(*args)
}
