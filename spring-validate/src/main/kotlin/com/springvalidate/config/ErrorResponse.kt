package com.springvalidate.config

import java.io.Serializable


class ErrorResponse(
    val timestamp: String? = null,
    val status: Int? = null,
    val message: Any,
    val path: String? = null,
    val trace: String? = null
) : Serializable