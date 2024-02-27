package com.example.partypic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText


class LoginActivity : AppCompatActivity() {

    var emailAddressInput: EditText? = null
    var passwordInput: EditText? = null
    var signUpButton: Button? = null
    var signinButton: Button? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("creation","creating signin screen")
        setContentView(R.layout.login_screen)
        // Set ui widgets
        setUI()
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
}