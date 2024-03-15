package com.example.DrinkMaster.modules.signup
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.provider.MediaStore
import android.net.Uri
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.example.DrinkMaster.R
import com.example.DrinkMaster.modules.login.LoginActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class SignupActivity : AppCompatActivity() {

    private val auth = Firebase.auth

    // Define callback after select image
    private lateinit var imageSelectionCallBack: ActivityResultLauncher<Intent>

    // Define all the variables
    private lateinit var firstNameInputLayout: TextInputLayout
    private lateinit var firstNameEditText: TextInputEditText

    private lateinit var lastNameInputLayout: TextInputLayout
    private lateinit var lastNameInputEditText: TextInputEditText

    private lateinit var emailAddressInputLayout: TextInputLayout
    private lateinit var emailAddressInputEditText: TextInputEditText


    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var passwordInputEditText: TextInputEditText


    private lateinit var passwordConfirmationInputLayout: TextInputLayout
    private lateinit var passwordConfirmationInputEditText: TextInputEditText


     private lateinit var signUpButton: Button
     private lateinit var signInButton: Button

     private lateinit var pickProfilepicButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("creation","creating signup screen")


        setContentView(R.layout.signup_screen)
        setUI()

    }

    // Define the ui of the screen
    fun setUI() {
        signUpButton = findViewById(R.id.SignupButtonSignUpScreen)
        pickProfilepicButton = findViewById(R.id.profilePicButtonSignUpScreen)
        signInButton = findViewById(R.id.AlreadyHaveAccountButtonSignUpScreen)

        // Define all button listeners
        defineImageSelectionCallBack()
        signUpButton?.setOnClickListener {
            checkNewUserDetails()
            // check if everything is valid
            // check if it already exist
            // create connection to the db for sign up
        }
        signInButton?.setOnClickListener{
            val intent= Intent(this@SignupActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        pickProfilepicButton?.setOnClickListener {
            Log.i("buttonClick", "pick profile pick button in signup screen clicked")
            openGallery()

        }
    }

    private fun checkNewUserDetails() {
        firstNameEditText = findViewById(R.id.editTextFirstNameSignUpScreen)
        firstNameInputLayout = findViewById(R.id.layoutTextFirstNameSignUpScreen)
        val firstNameValue = firstNameEditText.text.toString().trim()

        lastNameInputEditText = findViewById(R.id.editTextLastNameSignUpScreen)
        lastNameInputLayout = findViewById(R.id.layoutTextLastNameSignUpScreen)
        val lastNameValue = lastNameInputEditText.text.toString().trim()

        emailAddressInputEditText = findViewById(R.id.editTextEmailAddressSignUpScreen)
        emailAddressInputLayout = findViewById(R.id.layoutTextEmailAddressSignUpScreen)
        val emailValue = emailAddressInputEditText.text.toString().trim()

        passwordInputEditText = findViewById(R.id.editTextPasswordSignUpScreen)
        passwordInputLayout = findViewById(R.id.layoutTextPasswordSignUpScreen)
        val passwordValue = passwordInputEditText.text.toString().trim()



        passwordConfirmationInputEditText = findViewById(R.id.editTextConfirmPasswordSignUpScreen)
        passwordConfirmationInputLayout = findViewById(R.id.layoutTextConfirmPasswordSignUpScreen)
        val passwordConfirmationValue = passwordConfirmationInputEditText.text.toString().trim()




        val checkUserValidation = userValidation(firstNameValue,lastNameValue,emailValue,passwordValue,passwordConfirmationValue)

        if(checkUserValidation) {
            Log.i("buttonClick", "signup button in signup screen clicked")
            Log.i("signupSubmit", "first Name Input is:" + firstNameValue)
            Log.i("signupSubmit", "Last Name Input is:" + lastNameValue)
            Log.i("signupSubmit", "email input is:" + emailValue)
            Log.i("signupSubmit", "password Input is:" + passwordValue)
            Log.i("signupSubmit", "password Confirmation Input is:" + passwordConfirmationValue)
//            auth.createUserWithEmailAndPassword(emailValue,passwordValue).addOnSuccessListener {
//                val authenticatedUser = it.user!!

//            }
        }
    }

    private fun userValidation(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (firstName.isEmpty()) {
            firstNameInputLayout.error = "First name cannot be empty"
            return false
        } else {
            firstNameInputLayout.error = null
        }
        if (lastName.isEmpty()) {
            lastNameInputLayout.error = "Last name cannot be empty"
            return false
        } else {
            lastNameInputLayout.error = null
        }
        if (email.isEmpty()) {
            emailAddressInputLayout.error = "Email cannot be empty"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailAddressInputLayout.error = "Invalid email format"
            return false
        } else {
            emailAddressInputLayout.error = null
        }
        if (password.isEmpty()) {
            passwordInputLayout.error = "Password cannot be empty"
            return false
        } else if (password.length < 6) {
            passwordInputLayout.error = "Password must be at least 6 characters"
            return false
        } else if (!password.any { it.isDigit() }) {
            passwordInputLayout.error = "Password must contain at least one digit"
            return false
        } else {
            passwordInputLayout.error = null
        }
        if (confirmPassword.isEmpty()) {
            passwordConfirmationInputLayout.error = "Confirm password cannot be empty"
            return false
        } else if (password != confirmPassword) {
            passwordConfirmationInputLayout.error = "Passwords do not match."
            return false
        } else {
            passwordConfirmationInputLayout.error = null
        }
        if (pickProfilepicButton == null) {
            Toast.makeText(
                this@SignupActivity,
                "You must select Profile Image",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    // Func to move for the gallery
    private fun openGallery() {
        val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
        imageSelectionCallBack.launch(intent)
    }

    @SuppressLint("Recycle")
    private fun getImageSize(uri: Uri?): Long {
        val inputStream = contentResolver.openInputStream(uri!!)
        return inputStream?.available()?.toLong() ?: 0
    }

    private fun defineImageSelectionCallBack() {
        imageSelectionCallBack = registerForActivityResult( StartActivityForResult()) { result: ActivityResult ->
            try {
                val imageUri: Uri? = result.data?.data
                if (imageUri != null) {
                    val imageSize = getImageSize(imageUri)
                    val maxCanvasSize = 5 * 1024 * 1024 // 5MB
                    if (imageSize > maxCanvasSize) {
                        Toast.makeText(
                            this@SignupActivity,
                            "Selected image is too large",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        pickProfilepicButton?.setImageURI(imageUri)
                    }

                } else {
                    Toast.makeText(this@SignupActivity, "No Image Selected", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@SignupActivity, "Error processing result", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


