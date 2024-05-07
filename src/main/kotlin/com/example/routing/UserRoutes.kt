package com.example.routing

import com.example.database.DatabaseConnection
import com.example.database.tablemodels.UserEntity
import com.example.models.user.UserGenericRequest
import com.example.models.user.UserRequest
import com.example.models.user.UserResponse
import com.example.utils.TokenManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.config.yaml.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.*
import org.mindrot.jbcrypt.BCrypt
import java.util.*


fun Routing.authRoutes(){


    registerUser()
    login()
}

fun Routing.registerUser(){
//        val url : String = "http://www.google.de"
//        val check : Boolean = "http" in url
    val db = DatabaseConnection.databaseNote

    post("/register"){
        val response=call.receive<UserResponse>()

        if (!response.isValidUser()){
            call.respond(
                HttpStatusCode.BadRequest,
                UserGenericRequest(
                    succes = false,
                    data = null,
                    message = "where @ in email ? must 6 character in password"
                )
            )
            return@post
        }else{
            val email=response.email.lowercase()
            val password= response.hashedPassword()
            //Check if username already exists
            val user=db.from(UserEntity).select()
                .where(UserEntity.email eq email)
                .map {
                    it[UserEntity.email]
                    UserGenericRequest(
                        succes = false,
                        data = null,
                        message = "username already exists")
                }
                .firstOrNull()

            if (user !=null){
                call.respond(user)
            }else{
                val insert=db.insert(UserEntity){
                    set(it.email,email)
                    set(it.password,password)
                }

                if (insert==1){
                    call.respond(
                        UserGenericRequest(
                            succes = true,
                            data = "User registered succesfuly",
                            message = null
                        )
                    )
                }else{
                    call.respond(
                        UserGenericRequest(
                            succes = false,
                            data = null,
                            message = "Fatal error"
                        )
                    )
                }
            }



        }
    }

}
fun Routing.login() {
    val db = DatabaseConnection.databaseNote
    post("/login"){

        val response=call.receive<UserResponse>()

        if (!response.isValidUser()){
            call.respond(
                HttpStatusCode.BadRequest,
                UserGenericRequest(
                    succes = false,
                    data = null,
                    message = "where @ in email ? must 6 character in password"
                )
            )
            return@post
        }else{
            val email=response.email.lowercase()
            val password= response.password

            val user=db.from(UserEntity).select()
                .where(UserEntity.email eq email)
                .map {
                    val id=it[UserEntity.id]
                    val email=it[UserEntity.email]
                    val password=it[UserEntity.password]
                    UserRequest(
                        id=id!!,
                        email=email!!,
                        password=password!!
                    )
                }.firstOrNull()

            if (user ==null){
                call.respond(
                    UserGenericRequest(
                        succes = false,
                        data = "Invalid username or password 1",
                        message = null
                    )
                )
                return@post
            }else{
                val doesPassMatch=BCrypt.checkpw(password,user.password)
                if (doesPassMatch){
                    call.respond(
                        UserGenericRequest(
                            succes = true,
                            data = UserRequest(
                                id = user.id,
                                email=user.email,
                                password=user.password
                            ),
                            message = "token"
                        )
                    )
                }else{
                    call.respond(
                        UserGenericRequest(
                            succes = false,
                            data = "Invalid username or password 2",
                            message = null
                        )
                    )
                }

            }


        }
    }

}