package com.example.quotify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context): ViewModel() {
    private lateinit var quoteList:Array<Quote>
    private var index=0
    private var size=0

    init{
        quoteList = loadQuotesFromAssets()
    }
    fun getQuote()=quoteList[index]
    fun nextQuote() = quoteList[if(index==size-1) index else ++index]
    fun previousQuote() = quoteList[if(index==0) index else --index]


    private fun loadQuotesFromAssets(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size:Int = inputStream.available()
        this.size=size
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer,Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json,Array<Quote>::class.java)
    }


}