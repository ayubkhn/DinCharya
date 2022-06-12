package com.khntech.dietchart.view.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import com.khntech.dietchart.GlobalFbInstance
import com.khntech.dietchart.R
import com.khntech.dietchart.view.OtpActivity
import com.khntech.dietchart.view.homepage.FragmentActivity
import com.khntech.dietchart.viewmodel.MainViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var emailTxt: EditText
    lateinit var passwordTxt: EditText
    lateinit var loginBtn: Button
    lateinit var sighUpTxt: TextView

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page1)

        emailTxt = findViewById(R.id.loginEmailTxt)
        passwordTxt = findViewById(R.id.loginPasswordTxt)
        loginBtn = findViewById(R.id.loginBtn)
        sighUpTxt = findViewById(R.id.signUpTxt)

        loginBtn.setOnClickListener {
            forLogin()
        }

        sighUpTxt.setOnClickListener {
            otpGeneration()
        }
    }

    private fun otpGeneration() {
        startActivity(Intent(this, OtpActivity::class.java))
    }

    private fun forLogin() {
        if (emailTxt.text.isBlank() || passwordTxt.text.isBlank()) {
            Toast.makeText(this, "Email Id and Password can't be empty", Toast.LENGTH_SHORT).show()
            return
        } else {
            mainViewModel.loginWithFireBase(emailTxt.text.toString(), passwordTxt.text.toString())

            mainViewModel.loginStatusResponse.observe(this, Observer {
                if (it == true) {
                    Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, FragmentActivity::class.java))
                } else
                    Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
            })

        }
    }
}