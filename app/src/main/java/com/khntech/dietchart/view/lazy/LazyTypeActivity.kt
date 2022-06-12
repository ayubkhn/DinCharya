package com.khntech.dietchart.view.lazy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.khntech.dietchart.R

class LazyTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lazy_type)
    }

    fun lazyGain(view: View) {
        startActivity(Intent(this, LazyGainActivity::class.java))

    }

    fun lazyLose(view: View) {
        startActivity(Intent(this, LazyLoseActivity::class.java))

    }

    fun lazyNormal(view: View) {
        startActivity(Intent(this, LazyNormalActivity::class.java))
    }

}