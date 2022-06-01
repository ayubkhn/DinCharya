package com.khntech.dietchart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.khntech.dietchart.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val name = intent.getDoubleExtra("NNN", 1.1)
        binding.nameSavedTxt.setText(name.toString())


        //       val myPrefer = getSharedPreferences("KEY", Context.MODE_PRIVATE)
//        val gen = myPrefer.getString("gender", "Not mentioned")
//        binding.genderSavedTxt.setText(gen)

//        val age = myPrefer.getInt("age", 0)
//        binding.ageSavedTxt.setText(age.toString())

//        val height = myPrefer.getFloat("height", 0.0F)
//        binding.heightSavedTxt.setText(height.toString())

//        val weight = myPrefer.getFloat("weight", 0.0F)
//        binding.weightSavedTxt.setText(weight.toString())

//        val email = myPrefer.getString("email", "not mentioned")
//        binding.emailSavedTxt.setText(email)

        loadData()

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
        binding.updateBtn.setOnClickListener {
            saveData()
        }

    }

    private fun loadData() {
//        val myPrefer = getSharedPreferences("NAME", Context.MODE_PRIVATE)
//        val name = myPrefer.getString("name", null)
//        binding.nameSavedTxt.setText(name)

        val my2Prefer = getSharedPreferences("KEY", Context.MODE_PRIVATE)
        val name = my2Prefer.getString("name", null)
        binding.nameSavedTxt.setText(name.toString())

        val age = my2Prefer.getInt("age", 0)
        binding.ageSavedTxt.setText(age.toString())

        val gen = my2Prefer.getString("gender", "Not mentioned")
        binding.genderSavedTxt.setText(gen.toString())

        val height = my2Prefer.getFloat("height", 0.0F)
        binding.heightSavedTxt.setText(height.toString())

        val weight = my2Prefer.getFloat("weight", 0.0F)
        binding.weightSavedTxt.setText(weight.toString())

        val email = my2Prefer.getString("email", "not mentioned")
        binding.emailSavedTxt.setText(email)

    }

    private fun saveData() {

//        val myPrefer = getSharedPreferences("NAME", Context.MODE_PRIVATE)
//        val editor = myPrefer.edit()
//        editor.putString("name", binding.nameSavedTxt.text.toString())
//        editor.apply()


        val my2Prefer = getSharedPreferences("KEY", Context.MODE_PRIVATE)
        val editor2 = my2Prefer.edit()

        editor2.putString("name", binding.nameSavedTxt.text.toString())
        editor2.putInt("age", binding.ageSavedTxt.text.toString().toInt())
        editor2.putString("gender", binding.genderSavedTxt.text.toString())
        editor2.putFloat("height", binding.heightSavedTxt.text.toString().toFloat())
        editor2.putFloat("weight", binding.weightSavedTxt.text.toString().toFloat())
        editor2.putString("email", binding.emailSavedTxt.text.toString())
        editor2.apply()
        Toast.makeText(this, "Saved Data", Toast.LENGTH_LONG).show()


    }

    private fun checkUser() {

        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            startActivity(Intent(this, Page1Activity::class.java))
            finish()
        } else {
            val phone: String? = firebaseUser.phoneNumber
            binding.phoneTv.text = phone


        }
    }

}