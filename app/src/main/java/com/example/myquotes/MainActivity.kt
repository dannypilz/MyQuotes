package com.example.myquotes

import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myquotes.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.dialog_new_quote.*

class MainActivity : AppCompatActivity() { //MainActivity erbt von "AppCompatActivity" welche für Abwärtskompatibilität da ist
    private lateinit var dialog: AlertDialog
    private lateinit var viewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main) //verbinden der MainActivity mit der xml activity_main
        viewModel = ViewModelProvider(this).get(QuoteViewModel::class.java) //passendes ViewModel uns geben lassen (da dieses über den Lebenszyklus hier hinaus geht können wir keine variable dafür einfach erstellen!)
        binding.viewmodel =  viewModel //binding.Name greift auf Variable in activity_main.xml zu!
        binding.lifecycleOwner = this //wenn Activity sich verändert werden die Daten im Lifecycle auch verändert

        val viewPager = binding.quoteViewPager //aus activity_main.xml ViewPager2 Element
        val adapter = QuoteViewPagerAdapter()
        viewPager.adapter = adapter

        //Immer wenn sich etwas in den Zitaten ändert wird der Observer aufgerufen und für die Schritte aus
        viewModel.quotes.observe(this, Observer { quotes -> //lambda Ausdruck
            adapter.setQuotes(quotes)
            if (viewModel.newQuoteAdded) { //nur wenn neues Zitat auch kommt und nicht Abgebrochen wurde
                viewPager.setCurrentItem(quotes.size - 1, true) //zum Ende plättern (zum neuen Zitat)
            }
        })
    }

    fun addQuote(view: View){
        //Dialog anzeigen
        val builder = AlertDialog.Builder(this) //aus androidx Packages!
        builder.apply {
            setTitle("Neues Zitat")
            setView(R.layout.dialog_new_quote)
            setPositiveButton("OK") {dialog, id -> //Lambda Ausdruck um Verhalten von OK-Button umzusetzen
                //Zitat erstellen und Liste anhängen
                createQuote()
            }
            setNegativeButton("Abbrechen",null) //Dialog wird einfach wieder geschlossen dann
        }
        dialog = builder.create() //Dialog anlegen der angezeigt werden soll
        dialog.show() // dialog anzeigen

    }

    private fun createQuote(){
        //Eingaben des Nutzers speichern
        val text = dialog.new_quote_text.text.toString()
        val author = dialog.new_quote_author.text.toString()
        val year = dialog.new_quote_year.text.toString()
        //neues Zitat erstellen
        viewModel.createQuote(text,author,year)
    }

}