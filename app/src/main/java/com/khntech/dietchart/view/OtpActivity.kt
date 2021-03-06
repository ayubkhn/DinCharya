package com.khntech.dietchart.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.khntech.dietchart.GlobalFbInstance
import com.khntech.dietchart.databinding.ActivityMainBinding
import com.khntech.dietchart.view.login.RegisterActivity
import java.util.concurrent.TimeUnit

class OtpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var forceResendingToken: PhoneAuthProvider.ForceResendingToken? = null
    private var mCallBacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null
    private var mVerificationId: String? = null
    private val TAG = "MAIN_TAG"

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.phoneLl.visibility = View.VISIBLE
        binding.codeLl.visibility = View.GONE

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)


        mCallBacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted")
                signInWithPhoneAuthCredential(phoneAuthCredential)

            }

            override fun onVerificationFailed(e: FirebaseException) {
                progressDialog.dismiss()
                Log.d(TAG, "onVerificationFailed : ${e.message}")

                Toast.makeText(this@OtpActivity, "${e.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d(TAG, "onCodeSent : $verificationId")
                mVerificationId = verificationId
                progressDialog.dismiss()
                Log.d(TAG, "onCodeSent : $verificationId")


                binding.phoneLl.visibility = View.GONE
                binding.codeLl.visibility = View.VISIBLE
                Toast.makeText(this@OtpActivity, "Verification Code sent..", Toast.LENGTH_SHORT)
                    .show()
                binding.codeSentDescriptionTv.text = "Please type verification code we sent to ${
                    binding.phoneEt.text.toString().trim()
                }"
            }

        }

        binding.codeSentDescriptionTv.setOnClickListener {
            startActivity(Intent(this, OtpActivity::class.java))
        }
        binding.phoneContinueBtn.setOnClickListener {

            val phone = binding.phoneEt.text.toString().trim()
            if (TextUtils.isEmpty(phone)) {
                Toast.makeText(this@OtpActivity, "Please Enter Your Number", Toast.LENGTH_SHORT)
                    .show()
            } else {
                startPhoneNumberVerification(phone)
            }
        }

        binding.resendCodeTv.setOnClickListener {

            val phone = binding.phoneEt.text.toString().trim()
            if (TextUtils.isEmpty(phone)) {
                Toast.makeText(this@OtpActivity, "Please Enter Your Number", Toast.LENGTH_SHORT)
                    .show()
            } else {
                resendVerificationCode(phone, forceResendingToken)
            }
        }

        binding.codeSubmitBtn.setOnClickListener {

            val code = binding.codeEt.text.toString().trim()
            if (TextUtils.isEmpty(code)) {
                Toast.makeText(
                    this@OtpActivity, "Please Enter Verification Code", Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                verificationPhoneNumberWithCode(mVerificationId, code)
            }
        }
    }

    private fun startPhoneNumberVerification(phone: String) {
        Log.d(TAG, "startPhoneVerification : $phone")

        progressDialog.setMessage("Verifying Phone Number...")
        progressDialog.show()

        val options = mCallBacks?.let {
            PhoneAuthOptions.newBuilder(GlobalFbInstance.getFirebaseInstance())
                .setPhoneNumber(phone)
                .setTimeout(6L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(it)
                .build()
        }

        if (options != null) {
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }

    private fun resendVerificationCode(
        phone: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        progressDialog.setMessage("Resending Code...")
        progressDialog.show()
        Log.d(TAG, "resendVerificationCode : $phone")


        val options = mCallBacks?.let {
            PhoneAuthOptions.newBuilder(GlobalFbInstance.getFirebaseInstance())
                .setPhoneNumber(phone)
                .setTimeout(6L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(it)
                .build()
        }

        if (options != null) {
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }

    private fun verificationPhoneNumberWithCode(verificationId: String?, code: String) {
        Log.d(TAG, "verifyPhoneNumberWithCode: $verificationId $code")

        progressDialog.setMessage("Verifying Code...")
        progressDialog.show()

        val credential = PhoneAuthProvider.getCredential(verificationId.toString(), code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        Log.d(TAG, "signInWithPhoneAuthCredential : ")
        progressDialog.setMessage(" Logging in...")
        GlobalFbInstance.getFirebaseInstance().signInWithCredential(credential)
            .addOnSuccessListener {

                progressDialog.dismiss()
                val phone = GlobalFbInstance.getFirebaseInstance().currentUser?.phoneNumber
                Toast.makeText(this, "Fill your info as $phone", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->

                progressDialog.dismiss()
                Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

}



       

