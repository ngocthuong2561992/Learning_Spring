package com.mysql.service

interface ApiService {
    fun testSave()

    fun handleData(): List<*>
}