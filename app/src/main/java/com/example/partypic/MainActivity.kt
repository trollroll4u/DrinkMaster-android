package com.example.partypic

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    private lateinit var emailAddressInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var signUpButton: Button
    private lateinit var signinButton: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("creation","creating signin screen")
        setContentView(R.layout.login_screen)
//        setContentView(R.layout.login_screen)
//        setUI()

//        val loginButton: Button = findViewById(R.id.onCreateButton)
//
//        loginButton.setOnClickListener(::onloginButtonClicked)
    }

    fun setUI(){
        emailAddressInput = findViewById(R.id.editLoginTextEmailAddress)
        passwordInput = findViewById(R.id.editLoginTextPassword)
        signinButton = findViewById(R.id.LoginButton)
        signUpButton = findViewById(R.id.toSignupPageButton)

        signUpButton?.setOnClickListener{
            Log.i("buttonClick","signup button in signin screen clicked")
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        signinButton?.setOnClickListener{
            Log.i("buttonClick","signup button in signin screen clicked")
            Log.i("signinSubmit","email input is:" + emailAddressInput?.text.toString())
            Log.i("signinSubmit","password Input is:" + passwordInput?.text.toString())

//            val intent = Intent(this, SignupActivity::class.java)
//            startActivity(intent)
        }
    }
    fun onloginButtonClicked(view:View){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}