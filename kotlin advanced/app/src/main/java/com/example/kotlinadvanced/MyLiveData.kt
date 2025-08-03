package com.example.kotlinadvanced

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MyLiveData : AppCompatActivity() {
    lateinit var liveDataViewModel: LiveDataViewModel
    lateinit var tvH:TextView
    lateinit var updateBtn:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_live_data)
        tvH=findViewById(R.id.tvH)
        updateBtn=findViewById(R.id.updateBtn)
       liveDataViewModel=ViewModelProvider(this).get(LiveDataViewModel::class.java)
        liveDataViewModel.factsLiveData.observe(this, Observer {
            //code
            tvH.text=it
        })
    updateBtn.setOnClickListener {
        liveDataViewModel.updateLiveData()
    }
    }
}