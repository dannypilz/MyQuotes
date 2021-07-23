//Klasse für alle BindingAdapter -> erzeugen neuen Tag für die XML Datei activity_main.xml
package com.example.myquotes

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("hideView")
fun hideView(view: View, hide: Boolean){ //hier wird in XML "isFirst" bzw. "isLast" übergeben und somit einfach ausgewertet
    view.visibility = if(hide) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("isGone")
fun isGone(view: View, gone: Boolean){ //hier wird in XML "isFirst" bzw. "isLast" übergeben und somit einfach ausgewertet
    view.visibility = if(gone) View.GONE else View.VISIBLE
}