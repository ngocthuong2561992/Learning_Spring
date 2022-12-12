package com.security.repository

import com.security.entity.RentalNewEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RentalNewRepository : JpaRepository<RentalNewEntity?, Int?> {

}