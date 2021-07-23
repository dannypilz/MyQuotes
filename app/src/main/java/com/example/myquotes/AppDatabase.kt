package com.example.myquotes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Klasse um Datenbank zu verbinden
@Database(entities = [Quote::class], version = 1)
abstract class AppDatabase  : RoomDatabase(){
    abstract fun quoteDao(): QuoteDao //Dao wird automatisch generiert nach der Art QuoteDao

    //Datenbank Verbindung herstellen (immer gleich, nicht komplet zu verstehen nötig)
    companion object { //Object der abstrakten Klasse erstellen bzw. auf eine funktion von einer solchen zugreifen können
        //hierrüber die Datenbank als Singelton erstellen (nur ein Object dieser Klasse kann existieren) dafür auch @Volatile und @Synchronized
        @Volatile private var instance: AppDatabase? = null //"?" = dies ist eine eigenschaft die NULL werden darf

        @Synchronized
        fun getDatabase(context: Context) : AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, //ruft exakt das jetzige Object der klasse auf
                    "quotes.db"
                ).build()
            }
            return instance!! //"!!" = ja Kotlin wir wissen, dass es null sein kann
        }
    }
}