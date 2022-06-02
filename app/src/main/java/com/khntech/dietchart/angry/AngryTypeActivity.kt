package com.khntech.dietchart.angry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.khntech.dietchart.R

class AngryTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_angry_type)
    }

    fun angerGain(view: View) {
        startActivity(Intent(this, AngryGainActivity::class.java))

    }

    fun angerLose(view: View) {
        startActivity(Intent(this, AngryLoseActivity::class.java))
    }

    fun angerNormal(view: View) {
        startActivity(Intent(this, AngryNormalActivity::class.java))
    }
}