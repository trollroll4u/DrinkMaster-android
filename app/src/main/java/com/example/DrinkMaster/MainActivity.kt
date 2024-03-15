package com.example.DrinkMaster

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    private lateinit var homeButton: Button
    private lateinit var searchButton: Button
    private lateinit var profileButton: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("creation","creating ypi screen")
        setContentView(R.layout.activity_main)
        setUI()

//        val loginButton: Button = findViewById(R.id.onCreateButton)
//
//        loginButton.setOnClickListener(::onloginButtonClicked)
    }

    fun setUI () {

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(bottomNavigationView, navController)
//        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fra)
//        homeButton = findViewById(R.id.editLoginTextPassword)
//        searchButton = findViewById(R.id.LoginButton)
//        profileButton = findViewById(R.id.toSignupPageButton)

//        signUpButton?.setOnClickListener{
//            Log.i("buttonClick","signup button in signin screen clicked")
//            val intent = Intent(this, SignupActivity::class.java)
//            startActivity(intent)
//        }
//
//        signinButton?.setOnClickListener{
//            Log.i("buttonClick","signup button in signin screen clicked")
//            Log.i("signinSubmit","email input is:" + emailAddressInput?.text.toString())
//            Log.i("signinSubmit","password Input is:" + passwordInput?.text.toString())

//            val intent = Intent(this, SignupActivity::class.java)
//            startActivity(intent)
//        }
    }
//    fun onloginButtonClicked(view:View){
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
//    }
}