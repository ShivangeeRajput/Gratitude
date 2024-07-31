package com.example.kotlinadvanced

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MyViewModel : AppCompatActivity() {

    lateinit var tvCount: TextView
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        val button: Button = findViewById(R.id.btn)
        tvCount = findViewById(R.id.tvCount)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

//        button.setOnClickListener {
//            increment()
//        }
        setText()

    }

    private fun setText() {
        tvCount.text = mainViewModel.count.toString()
    }

    private fun increment(view: View) {
        mainViewModel.increment()
        setText()
    }
}
