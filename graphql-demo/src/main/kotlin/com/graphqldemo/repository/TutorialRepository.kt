package com.graphqldemo.repository

import com.graphqldemo.enity.Tutorial
import org.springframework.data.jpa.repository.JpaRepository

interface TutorialRepository : JpaRepository<Tutorial?, Long?>