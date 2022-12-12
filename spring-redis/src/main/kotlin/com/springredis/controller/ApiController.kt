package com.springredis.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.springredis.service.SalaryService
import com.springredis.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.CacheManager
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = ["/api"])
class ApiController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var salaryService: SalaryService

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    @Autowired
    lateinit var cacheManager: CacheManager

    @GetMapping("/testCache")
    fun testCache() {
        logger.info("------------------ demo @Cacheable --------------------");
        logger.info("find user with id = 1: {}", userService.findUserById(1));
        logger.info("find user with id = 1: {}", userService.findUserById(1));
        logger.info("find user with id = 2: {}", userService.findUserById(2));
        logger.info("find user with id = 2: {}", userService.findUserById(2));
        logger.info("------------------ demo @CacheEvict --------------------");
        userService.clearCache();
        logger.info("find user with id = 1: {}", userService.findUserById(1));
        logger.info("find user with id = 2: {}", userService.findUserById(2));
        logger.info("------------------ demo @CachePut --------------------");
        logger.info("reload and find user with id = 1: {}", userService.reloadAndFindUserById(1));
        logger.info("find user with id = 1: {}", userService.findUserById(1));
        logger.info("find user with id = 2: {}", userService.findUserById(2));
    }

    @GetMapping("/getEmployeeWithSalary")
    fun getEmployeeWithSalary(@RequestParam salary: Int, @RequestParam gender: String): List<*> {
        return salaryService.getEmployeeWithSalary(salary, gender)
    }

    @PostMapping("/clearSingleCache")
    fun clearSingleCache(@RequestParam cacheName: String, @RequestParam cacheKey: String) {
        cacheManager.getCache(cacheName)!!.evict(cacheKey)
    }

    @PostMapping("/clearAllCache")
    fun clearAllCache(@RequestParam cacheName: String) {
        cacheManager.getCache(cacheName)!!.clear()
    }

}