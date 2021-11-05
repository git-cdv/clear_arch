package com.chkan.cleanarchitecturebeerlist.features.beers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chkan.cleanarchitecturebeerlist.App
import com.chkan.cleanarchitecturebeerlist.R
import com.chkan.cleanarchitecturebeerlist.features.beers.vm.BeersViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.text_activity)

        val component = (application as App).appComponent
        val viewModelFactory = component.getViewModelFactory()

        val viewModel = ViewModelProvider(this, viewModelFactory).get(BeersViewModel::class.java)

        viewModel.beersLiveData.observe(this, Observer {
            textView.text = it.size.toString()
        })

    }
}