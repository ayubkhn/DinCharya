package com.khntech.dietchart.view.normal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.khntech.dietchart.R
import com.khntech.dietchart.view.NormalNor

class SelectTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal_type2)
    }

    fun normalGain(view: View) {
        startActivity(Intent(this, NormalGainActivity::class.java))

    }

    fun normalLose(view: View) {
        startActivity(Intent(this, NormalLoseActivity::class.java))

    }

    fun normalNormal(view: View) {
        startActivity(Intent(this, NormalNor::class.java))

    }
}