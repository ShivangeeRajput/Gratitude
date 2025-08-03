package com.example.kotlinadvanced

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel:ViewModel() {
    //mutable live data can be changed where as live data can be observed only
    val factsLiveData=MutableLiveData<String>("This is a fact")

    fun updateLiveData(){
        factsLiveData.value="Another fact"

    }

}