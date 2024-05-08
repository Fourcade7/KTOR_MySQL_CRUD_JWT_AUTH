package com.example.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.models.user.UserRequest
import com.example.models.user.UserResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*



fun Application.configureSecurity() {
    // Please read the jwt property from the config file if you are using EngineMain
    val jwtAudience = "jwt-audience"
    val jwtDomain = "https://jwt-provider-domain/"
    val jwtRealm = "jwt.realm"
    val jwtSecret = "jwt.secret"
    val jwtIssuer = "jwt.issuer"
    authentication {
        jwt("auth-jwt") {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtIssuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.getClaim("key").asString() != "") {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, hashMapOf("token:" to "Token is not valid or has expired"))
            }
        }
    }

}


class TokenManager {

    fun generateJWTToken(value1:String,value2:String):String{
        val jwtAudience = "jwt-audience"
        val jwtDomain = "https://jwt-provider-domain/"
        val jwtRealm = "jwt.realm"
        val jwtSecret = "jwt.secret"
        val jwtIssuer = "jwt.issuer"

        // Check username and password
        // ...
        val token = JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(jwtIssuer)
            .withClaim("key", value1)
            .withClaim("keyforpass", value2)
            .withExpiresAt(Date(System.currentTimeMillis() + 3_600_000))
            .sign(Algorithm.HMAC256(jwtSecret))

        return token

    }
}