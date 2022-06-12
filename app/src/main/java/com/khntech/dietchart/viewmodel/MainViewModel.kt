package com.khntech.dietchart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khntech.dietchart.GlobalFbInstance

class MainViewModel : ViewModel() {

    private var loginStatus = MutableLiveData<Boolean>()
    val loginStatusResponse: LiveData<Boolean> get() = loginStatus;


    private var registerStatus = MutableLiveData<Boolean>()
    val registerStatusResponse: LiveData<Boolean> get() = registerStatus;


    fun loginWithFireBase(email: String, password: String) {
        GlobalFbInstance.getFirebaseInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    loginStatus.postValue(true)
                }
            }
            .addOnFailureListener {
                loginStatus.postValue(false)

            }
    }


    fun registerWithFirebase(email: String, password: String) {
        GlobalFbInstance.getFirebaseInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    registerStatus.postValue(true)
                }
            }
            .addOnFailureListener {
                registerStatus.postValue(false)

            }
    }
}