package com.graphqldemo.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.graphqldemo.enity.Author

interface AuthorRepository : JpaRepository<Author?, Long?>