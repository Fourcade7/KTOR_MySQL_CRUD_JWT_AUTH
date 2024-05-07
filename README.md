New Project

        
        https://start.ktor.io/?_ga=2.173136099.1139195154.1714301259-811798944.1714077046&_gl=1*15r6u05*_ga*ODExNzk4OTQ0LjE3MTQwNzcwNDY.*_ga_9J976DJZ68*MTcxNDQ3NTYyNi4xNi4xLjE3MTQ0NzU2MzEuNTUuMC4w





Creating fat JARs using the Ktor Gradle plugin Open the build.gradle.kts

        plugins {
            id("io.ktor.plugin") version "2.3.10"
        }

        application {
            mainClass.set("com.example.ApplicationKt")
        }
        ktor {
            fatJar {
                archiveFileName.set("fat.jar")
            }
        }

Build and run a fat JAR﻿
The Ktor plugin provides the following tasks for creating and running fat JARs:

buildFatJar: builds a combined JAR of a project and runtime dependencies. You should see the ***-all.jar file in the build/libs directory when this build completes.

runFatJar: builds a fat JAR of a project and runs it.


Json Serialization

        plugins {
            kotlin("jvm") version "1.9.23"
            id("io.ktor.plugin") version "2.3.10"
            kotlin("plugin.serialization") version "1.9.23" //https://kotlinlang.org/docs/serialization.html#add-plugins-and-dependencies
        }
        //https://ktor.io/docs/serialization.html#serialization_dependency
        implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
        implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

Database Connection MySql

        // https://mvnrepository.com/artifact/org.ktorm/ktorm-core
        implementation("org.ktorm:ktorm-core:3.6.0")
        // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
        implementation("com.mysql:mysql-connector-j:8.3.0")


Run the Java Application in Background:

        java -jar MyApp.jar &

Close the Java Application:

        ps aux | grep "MyApp.jar"
        kill <PID>
        killall -21 java
  
