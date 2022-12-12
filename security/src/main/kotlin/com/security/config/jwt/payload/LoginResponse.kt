package com.security.config.jwt.payload

import java.util.*

class LoginResponse(
    val accessToken: String? = null,
    val issuedAt: String? = null,
    val expiration: String? = null
) {
    val tokenType = "Bearer"
}