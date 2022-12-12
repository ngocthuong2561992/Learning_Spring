package com.mysql.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.mysql.repository.TitleRepository
import com.mysql.service.TitleService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class TitleServiceImpl : TitleService {
    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var titleRepository: TitleRepository

    @Transactional
    override fun saveTitle(title: String) {
        val entity = titleRepository!!.findById(253406).get()
        entity.title = title
        titleRepository.save(entity)
    }

}