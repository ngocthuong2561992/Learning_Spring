package com.springredis.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.springredis.common.FunctionCommonUtils
import com.springredis.repository.entity.SalariesEntity
import com.springredis.repository.entity.UserEntity
import com.springredis.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service


@Service
class UserServiceImpl : UserService {
    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    @Autowired
    lateinit var functionCommonUtils: FunctionCommonUtils

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Cacheable("user")
    override fun findUserById(id: Int): UserEntity {
        simulateSlowService()
        return UserEntity(id, "Any name")
    }

//    clear cache by id
    @CacheEvict("user")
    override fun clearCacheById(id: Int) {

    }

//    clear all cache
    @CacheEvict(value = arrayOf("user"), allEntries = true)
    override fun clearCache() {

    }

//    override cache
    @CachePut(value = arrayOf("user"))
    override fun reloadAndFindUserById(id: Int): UserEntity {
        simulateSlowService()
        return UserEntity(id, "reload Any name")
    }

    private fun simulateSlowService() {
        try {
            val time = 3000L
            Thread.sleep(time)
        } catch (e: InterruptedException) {
            throw IllegalStateException(e)
        }
    }
}