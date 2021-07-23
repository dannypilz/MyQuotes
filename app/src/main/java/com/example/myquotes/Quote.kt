package com.example.myquotes

import androidx.room.Entity
import androidx.room.PrimaryKey

//steht für eine Tabelle
@Entity(tableName = "quotes")
data class Quote(
    val text: String,
    val author: String,
    val year: String,
    //an die letzte stelle damit konstruktor gleich bleiben kann
    @PrimaryKey(autoGenerate = true) val id: Long = 0, //id automatisch als Primary Key bestimmt + initialisiert
)