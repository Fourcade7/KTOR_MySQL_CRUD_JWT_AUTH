package com.example.models.note

import kotlinx.serialization.Serializable

@Serializable()
data class NoteResponse constructor(
    val notemessage:String
)
