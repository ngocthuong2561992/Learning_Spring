package com.security.config.exceptionHandler.exceptions

import java.lang.Exception

class CommonException : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
}