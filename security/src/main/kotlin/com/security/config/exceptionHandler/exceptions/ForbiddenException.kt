package com.security.config.exceptionHandler.exceptions

import java.lang.Exception

class ForbiddenException : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
}