package com.example


import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.models.user.UserResponse
import com.example.plugins.*
import com.example.utils.TokenManager
import com.typesafe.config.ConfigFactory
import io.ktor.http.*


import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*

import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.util.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    install(ContentNegotiation) {
        json()
    }



    val secret ="jwt.secret"
    val issuer ="jwt.issuer"
    val audience = "jwt.audience"
    val myRealm = "jwt.realm"
    authentication {
        jwt() {

        }

    }




    routing {
        post("/login2") {
            val user = call.receive<UserResponse>()
            // Check username and password
            // ...
            val token = JWT.create()
                .withAudience(audience)
                .withIssuer(issuer)
                .withClaim("username", user.email)
                .withExpiresAt(Date(System.currentTimeMillis() + 60000))
                .sign(Algorithm.HMAC256(secret))
            call.respond(hashMapOf("token" to token))
        }

    }
    configureRouting()
    //DatabaseConnection.databaseNote


}


@Serializable()
data class User constructor(val username:String)

fun Application.configureSecurity() {
    // Please read the jwt property from the config file if you are using EngineMain
    val jwtAudience = "jwt-audience"
    val jwtDomain = "https://jwt-provider-domain/"
    val jwtRealm = "ktor sample app"
    val jwtSecret = "secret"
    authentication {
        jwt {

        }
    }

    routing {
        post("/login2") {
            val user = call.receive<User>()
            // Check username and password
            // ...
            val token = JWT.create()
                .withAudience(jwtAudience)
                .withClaim("username", user.username)
                .withExpiresAt(Date(System.currentTimeMillis() + 60000))
                .sign(Algorithm.HMAC256(jwtSecret))
            call.respond(hashMapOf("token" to token))
        }
    }
}
