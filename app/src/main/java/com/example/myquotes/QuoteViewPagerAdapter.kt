package com.example.myquotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.quote.view.* //braucht "id 'kotlin-android-extensions'" in gradle app

class QuoteViewPagerAdapter :RecyclerView.Adapter<QuoteViewPagerAdapter.QuoteViewHolder>() { //Holder bekommen wir als innere klasse
    private var quotes = emptyList<Quote>()

    //Unterklasse
    inner class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val content = itemView.quote_text //Syntax dank "import kotlinx.android.synthetic.main.quote.view.*" möglich
        val author = itemView.quote_author
        val year = itemView.quote_year
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        //erstelle View mit quote.xml
        val quoteView = LayoutInflater.from(parent.context).inflate(R.layout.quote, parent,false)
        return QuoteViewHolder(quoteView)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        with(quotes[position]){
            holder.content.text = text
            holder.author.text = author
            holder.year.text = year
        }
    }

    override fun getItemCount() = quotes.size


    fun setQuotes(quotes: List<Quote>){
        this.quotes = quotes
        notifyDataSetChanged() //Update der Daten die im Adapter sind (immer nötig wenn Daten geändert werden)
    }

}