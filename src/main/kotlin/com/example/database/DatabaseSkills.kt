package com.example.database

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable
import org.ktorm.database.Database
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import java.io.File


//object DatabseConnection {
//
//    val database: Database = Database.connect(
//        url = "jdbc:mysql://localhost:8889/Users",
//        driver = "com.mysql.cj.jdbc.Driver",
//        user = "root",
//        password = "root"
//    )
//}

//object UserEntity: Table<Nothing>(tableName = "userreg") {
//
//    val id = int("id").primaryKey()
//    val username = varchar("username")
//    val password = varchar("password")
//}

//@Serializable
//data class UserResponse constructor(
//
//    val username:String,
//    val password:String
//)

//NOTE APP

//        get("/getallnotes") {
//
//            val notes = db.from(Note).select()
//                .map {
//                    val id = it[Note.id]
//                    val note = it[Note.note]
//                    NotesResponse(id = id ?: -1, note = note ?: "")
//                }
//            val notes2 = mutableListOf<NotesResponse>()
//            for (note in notes) {
//                notes2.add(NotesResponse(id = note[Note.id] ?: -1, note = note[Note.note] ?: ""))
//            }
//
//            call.respond(notes2.reversed())
//
//        }


//        post("/addnotes") {
//            val data = call.receive<NotesResponse>()
//            val insert = db.insert(Note) {
//                set(it.id, 0)
//                set(it.note, data.note)
//            }
//
//            if (data.note is String) {
//                call.respond(HttpStatusCode.OK, "added")
//            } else {
//                call.respond(HttpStatusCode.BadRequest, "bad")
//            }
//
//        }



//        get("getnote/{id}") {
//            val id=call.parameters.get("id")?.toInt()?:-1
//
//            val notebyid= db.from(Note).select()
//                .where{Note.id eq id}
//                .map {
//                    val idd=it[Note.id]
//                    val note=it[Note.note]
//                    NotesResponse(id = idd!!,note=note!!)
//                }.firstOrNull()
//
//
//            if (notebyid!=null){
//                call.respond(HttpStatusCode.OK,notebyid)
//            }else{
//                call.respond(HttpStatusCode.NotFound,NotesResponse(id=0, note = "Not Found"))
//            }
//
//        }


//        put("/updatenote/{id}"){
//            val id=call.parameters.get("id")?.toInt()?:-1
//            val updatenote=call.receive<NotesResponse>()
//
//            val updateedit=db.update(Note){
//                set(it.note,updatenote.note)
//                where {
//                    it.id eq id
//                }
//            }
//
//            if (updateedit==1){
//                call.respondText("Updated")
//            }else{
//                call.respondText("False")
//            }
//        }


//        delete("/deletenote/{id}") {
//            val id=call.parameters.get("id")?.toInt()?:-1
//            val del = db.delete(Note){
//                it.id eq id
//            }
//
//            if (del==1){
//                call.respondText("Deleted")
//            }else{
//                call.respondText("False")
//            }
//        }

//NOTE APP


//    val array= mutableListOf<Int>()
//    array.map {
//
//    }.filter {
//        true
//    }


//    database.insert(User) {
//        set(it.id, 0)
//        set(it.name, "Fourcade3050")
//    }
//
//    val users= database.from(User).select()
//    for (row in users){
//        println("${row[User.id]}: ${row[User.name]}")
//    }

//    database.update(User){
//        set(it.name, readLine().toString())
//        where {
//            it.id eq 1
//        }
//    }

//    database.delete(User){
//        it.id eq 4
//    }



//QUERY PARAMS

//    get("/req") {
//        println("my URL ${call.request.uri}")
//        println("my URL ${call.request.headers.names()}")
//        println("my URL ${call.request.headers.get("Accept")}")
//        println("my URL Querys ${call.request.queryParameters.names()}")
//        println("my URL Querys ${call.request.queryParameters.get("name")}")
//    }
//    get("/page/{id}") {
//        val pagenumber=call.parameters.get("id")
//        println("page number: $pagenumber")
//        call.respondText("page number: $pagenumber")
//    }


//DOWNLOAD AND OPEN

//    get("/home") {
//        //val file=File("files/polov.jpeg")
//        //val file = File("files/index.html")
//        call.response.header(
//            HttpHeaders.ContentDisposition,
//            ContentDisposition.Inline.withParameter(
//                ContentDisposition.Parameters.FileName,
//                "downloadimage.jpeg"
//            ).toString()
//        )
//
//        call.respondFile(file)
//
//    }




