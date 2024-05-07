package com.example.models.note

import kotlinx.serialization.Serializable




@Serializable()
data class NoteRequest constructor(
    val id:Int,
    val notemessage:String
)



@Serializable()
data class NoteGenericRequest<T> constructor(
    val succes:Boolean,
    val data:T?,
    val message:String

)