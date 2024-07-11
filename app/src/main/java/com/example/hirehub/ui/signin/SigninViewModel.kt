package com.example.hirehub.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignInViewModel : ViewModel() {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    fun signIn(email: String, password: String): LiveData<String> {
        val signInResult = MutableLiveData<String>()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    signInResult.value = "Success"
                } else {
                    signInResult.value = task.exception?.message ?: "Sign In Failed"
                }
            }
        return signInResult
    }
}
