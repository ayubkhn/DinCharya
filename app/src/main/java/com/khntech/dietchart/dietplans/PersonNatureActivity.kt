package com.khntech.dietchart.dietplans

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.khntech.dietchart.R
import com.khntech.dietchart.view.angry.AngryTypeActivity
import com.khntech.dietchart.view.lazy.LazyTypeActivity
import com.khntech.dietchart.view.normal.SelectTypeActivity

class PersonNatureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nature)
    }

    fun angerIssue(view: View) {
        startActivity(Intent(this, AngryTypeActivity::class.java))
    }

    fun lazy(view: View) {
        startActivity(Intent(this, LazyTypeActivity::class.java))
    }

    fun inActive(view: View) {
        startActivity(Intent(this, LazyTypeActivity::class.java))
    }

    fun decent(view: View) {
        startActivity(Intent(this, SelectTypeActivity::class.java))
    }

    fun calm(view: View) {
        startActivity(Intent(this, SelectTypeActivity::class.java))
    }

    fun aggressive(view: View) {
        startActivity(Intent(this, AngryTypeActivity::class.java))
    }

}