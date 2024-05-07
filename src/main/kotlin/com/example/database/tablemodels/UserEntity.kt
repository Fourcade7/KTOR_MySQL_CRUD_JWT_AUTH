package com.example.database.tablemodels

import com.example.database.tablemodels.NoteEntity.primaryKey
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar


object UserEntity : Table<Nothing>(tableName = "f7users") {

    val id = int("id").primaryKey()
    val email = varchar("email")
    val password = varchar("password")

}