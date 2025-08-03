package com.example.kotlinadvanced
import androidx.lifecycle.ViewModel
class MainViewModel: ViewModel() {

    var count:Int=0

    fun increment(){
        count++
    }
}