package com.graphqldemo.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.graphqldemo.enity.Author
import com.graphqldemo.enity.Tutorial
import com.graphqldemo.repository.AuthorRepository
import com.graphqldemo.repository.TutorialRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class Mutation : GraphQLMutationResolver {

    @Autowired
    lateinit var authorRepository: AuthorRepository

    @Autowired
    lateinit var tutorialRepository: TutorialRepository

    fun createAuthor(name: String?, age: Int?): Author {
        val author = Author()
        author.name = name
        author.age = age
        authorRepository.save(author)
        return author
    }

    fun createTutorial(title: String?, description: String?, authorId: Long?): Tutorial {
        val book = Tutorial()
        book.author = Author(id = authorId)
        book.title = title
        book.description = description
        tutorialRepository.save(book)
        return book
    }

    fun deleteTutorial(id: Long?): Boolean {
        tutorialRepository.deleteById(id!!)
        return true
    }

    fun updateTutorial(id: Long?, title: String?, description: String?): Tutorial {
        val optTutorial: Optional<Tutorial?> = tutorialRepository.findById(id!!)
        if (optTutorial.isPresent()) {
            val tutorial: Tutorial = optTutorial.get()
            if (title != null) tutorial.title = title
            if (description != null) tutorial.description = description
            tutorialRepository.save(tutorial)
            return tutorial
        }
        throw Exception("Not found Tutorial to update!")
    }

}