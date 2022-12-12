package com.mysql.repository

import com.mysql.repository.entity.TitleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TitleRepository : JpaRepository<TitleEntity?, Int?> {

}