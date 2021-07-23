package com.example.myquotes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuoteDao {
    //SELECT
    @Query("SELECT * FROM quotes ORDER BY id ASC")
    fun getQuotes(): LiveData<List<Quote>> //Name der Select Anweisung oben festlegen -> hier können auch Parameter übergeben werden für die Abfrage

    //INSERT
    @Insert
    suspend fun insert(quote:Quote) //Insert into quotes... wird von Room automatisch erstellt!
    //suspend -> Datenbank wird im hintergrund aktuallisiert -> keine Laufzeit probleme

}