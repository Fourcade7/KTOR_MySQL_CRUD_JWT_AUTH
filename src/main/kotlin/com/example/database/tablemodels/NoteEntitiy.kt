package com.example.database.tablemodels

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar


object NoteEntity : Table<Nothing>(tableName = "f7notes") {

    val id = int("id").primaryKey()
    val notemessage = varchar("notemessage")

}