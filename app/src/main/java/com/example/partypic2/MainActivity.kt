package com.example.partypic2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton: Button = findViewById(R.id.onCreateButton)

        loginButton.setOnClickListener(::onloginButtonClicked)
    }

    fun onloginButtonClicked(view:View){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}