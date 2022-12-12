package com.graphqldemo.resolver

import com.coxautodev.graphql.tools.GraphQLResolver
import com.graphqldemo.enity.Author
import com.graphqldemo.enity.Tutorial
import com.graphqldemo.repository.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class TutorialResolver : GraphQLResolver<Tutorial?> {
    @Autowired
    lateinit var authorRepository: AuthorRepository

    fun getAuthor(tutorial: Tutorial): Optional<Author?> {
        return authorRepository.findById(tutorial!!.author!!.id!!)
    }
}