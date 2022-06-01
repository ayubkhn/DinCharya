package com.khntech.dietchart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Page1Activity : AppCompatActivity() {

    lateinit var emailTxt: EditText
    lateinit var passwordTxt: EditText
    lateinit var loginBtn: Button
    lateinit var sighUpTxt: TextView
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page1)

        emailTxt = findViewById(R.id.loginEmailTxt)
        passwordTxt = findViewById(R.id.loginPasswordTxt)
        loginBtn = findViewById(R.id.loginBtn)
        sighUpTxt = findViewById(R.id.signUpTxt)

        auth = FirebaseAuth.getInstance()
        loginBtn.setOnClickListener {
            forLogin()
        }

        sighUpTxt.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
    }

    private fun forLogin() {
        if (emailTxt.text.isBlank() || passwordTxt.text.isBlank()) {
            Toast.makeText(this, "Email Id and Password can't be empty", Toast.LENGTH_SHORT).show()
            return
        } else {
            auth.signInWithEmailAndPassword(emailTxt.text.toString(), passwordTxt.text.toString())
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, FragmentActivity::class.java))
                    } else {
                        Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()

                    }
                }
        }
    }
}