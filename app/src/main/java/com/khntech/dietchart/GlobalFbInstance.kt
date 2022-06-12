package com.khntech.dietchart

import com.google.firebase.auth.FirebaseAuth

object GlobalFbInstance {


    private lateinit var auth: FirebaseAuth


    fun getFirebaseInstance(): FirebaseAuth {
        auth = FirebaseAuth.getInstance()
        return auth;

    }
}