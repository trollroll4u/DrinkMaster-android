package com.example.partypic2
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.provider.MediaStore
import android.net.Uri
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult


class SignupActivity : AppCompatActivity() {

    // Define callback after select image
    private lateinit var imageSelectionCallBack: ActivityResultLauncher<Intent>

    // Define all the variables
    var firstNameInput: EditText? = null
    var lastNameInput: EditText? = null
    var emailAddressInput: EditText? = null
    var passwordInput: EditText? = null
    var passwordConfirmationInput: EditText? = null
    var signUpButton: Button? = null
    var pickProfilepicButton: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("creation","creating signup screen")
        setContentView(R.layout.signup_screen)
        setUI()

    }

    // Define the ui of the screen
    fun setUI() {
        firstNameInput = findViewById(R.id.editTextFirstName)
        lastNameInput = findViewById(R.id.editTextLastName)
        emailAddressInput = findViewById(R.id.editTextEmailAddress)
        passwordInput = findViewById(R.id.editTextPassword)
        passwordConfirmationInput = findViewById(R.id.editTextConfirmPassword)
        signUpButton = findViewById(R.id.SignupButton)
        pickProfilepicButton = findViewById(R.id.profilePicButton)
        //
        defineImageSelectionCallBack()


        signUpButton?.setOnClickListener {
            Log.i("buttonClick", "signup button in signup screen clicked")
            Log.i("signupSubmit", "first Name Input is:" + firstNameInput?.text.toString())
            Log.i("signupSubmit", "Last Name Input is:" + lastNameInput?.text.toString())
            Log.i("signupSubmit", "email input is:" + emailAddressInput?.text.toString())
            Log.i("signupSubmit", "password Input is:" + passwordInput?.text.toString())
            Log.i("signupSubmit", "password Confirmation Input is:" + passwordConfirmationInput?.text.toString()
            )
            // check if everything is valid
            // check if it already exist
            // create connection to the db for sign up
        }


        pickProfilepicButton?.setOnClickListener {
            Log.i("buttonClick", "pick profile pick button in signup screen clicked")
            openGallery()

        }
    }
    // Func to move for the gallery
    private fun openGallery() {
        val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
        imageSelectionCallBack.launch(intent)
    }

    private fun defineImageSelectionCallBack() {
        imageSelectionCallBack = registerForActivityResult( StartActivityForResult()) { result: ActivityResult ->
            try {
                val imageUri: Uri? = result.data?.data
                if (imageUri != null) {
                    pickProfilepicButton?.setImageURI(imageUri)
                } else {
                    Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Error processing result", Toast.LENGTH_SHORT).show()
            }
        }
    }
}