package com.mysql.enums

import java.lang.IllegalArgumentException

enum class Gender(val detail: String) {
    M(""),
    F("");

    companion object {
        @JvmStatic
        fun fromString(code : String): Gender =
            values().find { value -> value.toString() == code } ?: throw IllegalArgumentException("No matching Gender for [${code}]")
    }
}