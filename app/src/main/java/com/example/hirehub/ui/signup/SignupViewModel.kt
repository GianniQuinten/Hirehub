package com.example.hirehub.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel : ViewModel() {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    fun signUp(email: String, password: String): LiveData<String> {
        val signUpResult = MutableLiveData<String>()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    signUpResult.value = "Success"
                } else {
                    signUpResult.value = task.exception?.message ?: "Sign Up Failed"
                }
            }
        return signUpResult
    }
}
