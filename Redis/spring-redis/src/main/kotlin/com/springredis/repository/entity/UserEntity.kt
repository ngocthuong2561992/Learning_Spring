package com.springredis.repository.entity

import java.io.Serializable

class UserEntity(
    var id: Int? = null,
    var name: String? = null
): Serializable {
    override fun toString(): String {
        return "{id: $id, name: $name}"
    }
}