package com.graphqldemo.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.graphqldemo.enity.Author
import com.graphqldemo.enity.Tutorial
import com.graphqldemo.repository.AuthorRepository
import com.graphqldemo.repository.TutorialRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Query: GraphQLQueryResolver {

    @Autowired
    lateinit var authorRepository: AuthorRepository

    @Autowired
    lateinit var tutorialRepository: TutorialRepository

    fun findAllAuthors(): MutableList<Author?> {
        return authorRepository.findAll()
    }

    fun findAllTutorials(): MutableList<Tutorial?> {
        return tutorialRepository.findAll()
    }

    fun countAuthors(): Long {
        return authorRepository.count()
    }

    fun countTutorials(): Long {
        return tutorialRepository.count()
    }
}