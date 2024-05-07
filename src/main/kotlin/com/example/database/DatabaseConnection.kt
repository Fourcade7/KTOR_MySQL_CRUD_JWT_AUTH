package com.example.database

import org.ktorm.database.Database

object DatabaseConnection {

    val databaseNote: Database = Database.connect(
        url = "jdbc:mysql://localhost:8889/Notes",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "root"
    )

//    val databaseUsers: Database = Database.connect(
//        url = "jdbc:mysql://localhost:8889/Users",
//        driver = "com.mysql.cj.jdbc.Driver",
//        user = "root",
//        password = "root"
//    )
}