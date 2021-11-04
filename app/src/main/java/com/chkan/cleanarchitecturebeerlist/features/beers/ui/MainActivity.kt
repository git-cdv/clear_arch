package com.chkan.cleanarchitecturebeerlist.features.beers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.chkan.cleanarchitecturebeerlist.App
import com.chkan.cleanarchitecturebeerlist.R
import com.chkan.cleanarchitecturebeerlist.data.datasource.api.BeersNetworkDataSource
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var source: BeersNetworkDataSource

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.text_activity)
        textView.text = source.number.toString()

    }
}