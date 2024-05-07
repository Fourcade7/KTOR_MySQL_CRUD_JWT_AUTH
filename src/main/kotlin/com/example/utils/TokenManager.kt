package com.example.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.models.user.UserRequest
import com.example.models.user.UserResponse
import io.ktor.server.application.*
import io.ktor.server.config.*
import java.util.*

class TokenManager constructor(
    val environment: ApplicationEnvironment
) {

    fun generateJWTToken(userRequest: UserRequest ):String{
        val secret = environment.config.property("jwt.secret").getString()
        val issuer = environment.config.property("jwt.issuer").getString()
        val audience = environment.config.property("jwt.audience").getString()
        val myRealm = environment.config.property("jwt.realm").getString()

        val token = JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("email", userRequest.email)
            .withExpiresAt(Date(System.currentTimeMillis() + 60000))
            .sign(Algorithm.HMAC256(secret))
        return token

    }
}