package com.springredis.service

import com.springredis.repository.entity.UserEntity

interface UserService {
    fun findUserById(id: Int): UserEntity
    fun clearCacheById(id: Int)
    fun clearCache()
    fun reloadAndFindUserById(id: Int): UserEntity
}