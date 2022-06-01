package com.khntech.dietchart

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class FragmentActivity : AppCompatActivity() {

    private lateinit var welcomeTxt: TextView
    private lateinit var ageTry1: TextView
    private lateinit var nameTxtShow: TextView
    private lateinit var profileImageBtn: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        welcomeTxt = findViewById(R.id.welcomeTxt)
        nameTxtShow = findViewById(R.id.nameTxtShow)
        profileImageBtn = findViewById(R.id.profileImageBtn)
        ageTry1 = findViewById(R.id.ageTry1)

        val myPrefer = getSharedPreferences("KEY", Context.MODE_PRIVATE)
        val name = myPrefer.getString("name", null)
        nameTxtShow.setText(name.toString())

        profileImageBtn.setOnClickListener {
            startActivity(
                Intent(this, ProfileActivity::class.java)
                    .putExtra("NNN", nameTxtShow.text.toString())

            )

        }
    }
}