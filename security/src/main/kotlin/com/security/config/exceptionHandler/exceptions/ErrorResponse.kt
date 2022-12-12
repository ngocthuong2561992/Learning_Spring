package com.security.config.exceptionHandler.exceptions

import java.io.Serializable

class ErrorResponse(
    val timestamp: String? = null,
    val status: Int? = null,
    val message: String? = null,
    val path: String? = null,
    val trace: String? = null
) : Serializable