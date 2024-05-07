package com.example.routing

import com.example.database.DatabaseConnection
import com.example.database.tablemodels.NoteEntity
import com.example.models.note.NoteGenericRequest

import com.example.models.note.NoteRequest
import com.example.models.note.NoteResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.*


fun Routing.noteRoutes() {

    insertNote()
    getAllNote()
    getnoteById()
    updateNote()
    deleteNote()


}

fun Routing.insertNote() {
    val db = DatabaseConnection.databaseNote

    post("/addnote") {
        val noteresponse = call.receive<NoteResponse>()
        if (noteresponse.notemessage.length < 3) {
            call.respond(
                HttpStatusCode.BadRequest,
                NoteGenericRequest(
                    succes = false,
                    data = null,
                    message = "message should be more than 3 characters"
                )
            )
        } else {
            val insert = db.insert(NoteEntity) {
                set(it.id, 0)
                set(it.notemessage, noteresponse.notemessage)
            }
            if (insert == 1) {
                call.respond(
                    HttpStatusCode.OK,
                    NoteGenericRequest(
                        succes = true,
                        NoteRequest(
                            id = 0,
                            notemessage = "Added succesfuly",
                        ),
                        message = "Added succesfuly"
                    )
                )
            } else {
                call.respond(
                    HttpStatusCode.BadRequest,
                    NoteGenericRequest(
                        succes = false,
                        data = null,
                        message = "Fatal Error"
                    )
                )
            }

        }


    }


}

fun Routing.getAllNote() {

    val db = DatabaseConnection.databaseNote

    get("/getallnotes") {
        val notes = db.from(NoteEntity).select()
            .map {
                val id = it[NoteEntity.id]
                val note = it[NoteEntity.notemessage]
                NoteRequest(id = id ?: -1, notemessage = note ?: "")
            }
        call.respond(notes)
    }


}

fun Routing.getnoteById() {
    val db = DatabaseConnection.databaseNote

    get("/getnote/{id}") {
        val id = call.parameters.get("id")?.toInt() ?: -1

        val note = db.from(NoteEntity).select()
            .where(NoteEntity.id eq id)
            .map {
                val id = it[NoteEntity.id]
                val note = it[NoteEntity.notemessage]
                NoteRequest(id = id ?: -1, notemessage = note ?: "")
            }.firstOrNull()

        if (note != null) {
            call.respond(NoteRequest(note.id, note.notemessage))
        } else {
            call.respond(
                NoteGenericRequest(
                    succes = false,
                    data = null,
                    message = "user not found"
                )
            )
        }

    }
}

fun Routing.updateNote() {
    val db = DatabaseConnection.databaseNote
    put("/updatenote/{id}") {
        val id = call.parameters.get("id")?.toInt() ?: -1
        val updatenote = call.receive<NoteResponse>()

        val updateedit = db.update(NoteEntity) {
            set(it.notemessage, updatenote.notemessage)
            where {
                it.id eq id
            }
        }

        if (updateedit == 1) {
            call.respond(
                NoteGenericRequest(
                    succes = true,
                    data = updatenote.notemessage,
                    message = "Updated succesfully"
                )
            )
        } else {
            call.respond(
                NoteGenericRequest(
                    succes = false,
                    data = null,
                    message = "Updated error"
                )
            )
        }
    }
}

fun Routing.deleteNote() {
    val db = DatabaseConnection.databaseNote
    delete("/deletenote/{id}") {
        val id = call.parameters.get("id")?.toInt() ?: -1
        val del = db.delete(NoteEntity) {
            it.id eq id
        }

        if (del == 1) {
            call.respond(
                NoteGenericRequest(
                    succes = true,
                    data = del,
                    message = "Deleted succesfully"
                )
            )
        } else {
            call.respond(
                NoteGenericRequest(
                    succes = false,
                    data = del,
                    message = "Delete error"
                )
            )
        }
    }
}
