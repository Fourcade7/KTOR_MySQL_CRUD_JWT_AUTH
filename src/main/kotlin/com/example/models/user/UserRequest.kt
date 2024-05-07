package com.example.models.user

import kotlinx.serialization.Serializable





@Serializable()
data class UserRequest constructor(
    val id:Int,
    val email:String,
    val password:String
)

@Serializable()
data class UserGenericRequest<T> constructor(
    val succes:Boolean,
    val data:T?,
    val message:String?
)


