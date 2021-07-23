package com.example.myquotes

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

//AndroidViewModel statt ViewModel wird für Room benötigt
class QuoteViewModel(application: Application) : AndroidViewModel(application) {
    //QuoteDao Object erhalten
    private var quoteDao = AppDatabase.getDatabase(application).quoteDao()
    val quotes = quoteDao.getQuotes() //mittels Room und Livedata -> hier wird zugriff automatisch im Hintergrund stattfiinden (kein "viewModelScope.launch" nötig)
    
    val hasNoQuotes = Transformations.map(quotes) { quotes.value.isNullOrEmpty() }
    var newQuoteAdded = false

    fun createQuote(text: String, author: String, year: String) =
        viewModelScope.launch { //hiermit wird der Code im Hintergrund ausgeführt (wie wir mittels "suspend" in der Dao angegeben haben) -> sonst Fehler
            newQuoteAdded = true
            quoteDao.insert(Quote(text, author, year))
        }
}