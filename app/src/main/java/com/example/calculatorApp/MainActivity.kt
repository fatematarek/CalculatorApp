package com.example.calculatorApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calculatorApp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.subBtn.setOnClickListener {
            val name = binding.nameInput.text.toString()  //get the name from the edit text
            if (name.isNotEmpty()) {
                // User has entered a name, start the next activity
                val cIntent = Intent(this, calcActivity::class.java)
                cIntent.putExtra("userName", name)
                startActivity(cIntent)
            } else {
                // User has not entered a name, show an error message or take appropriate action
                Toast.makeText(this, "invalid input", Toast.LENGTH_SHORT).show()
            }

        }
    }
}