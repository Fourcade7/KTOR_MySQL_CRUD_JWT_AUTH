package com.example.models.user

import kotlinx.serialization.Serializable
import org.mindrot.jbcrypt.BCrypt


@Serializable()
class UserResponse constructor(

    val email:String,
    val password:String
) {

    fun hashedPassword():String{
        
        return BCrypt.hashpw(password,BCrypt.gensalt())
    }

    fun isValidUser():Boolean{
        return email.contains("@") && password.length>=6
    }

}