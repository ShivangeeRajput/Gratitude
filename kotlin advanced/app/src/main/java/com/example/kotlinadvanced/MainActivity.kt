package com.example.kotlinadvanced

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinadvanced.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences=getSharedPreferences("NoteData",Context.MODE_PRIVATE)

        binding.buttonSave.setOnClickListener {
            val note=binding.etNote.text.toString()
            val sharedEdit=sharedPreferences.edit()
            sharedEdit.putString("note",note)
            sharedEdit.apply()
            Toast.makeText(this,"Added Successfully",Toast.LENGTH_SHORT)
            binding.etNote.text.clear()
        }
        binding.displayButton.setOnClickListener {
            val storedNote=sharedPreferences.getString("note","")
            binding.displayNote.text="$storedNote"
        }
    }
}