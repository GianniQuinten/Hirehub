package com.example.hirehub.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hirehub.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignInViewModel : ViewModel() {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    fun signIn(email: String, password: String): LiveData<String> {
        val signInResult = MutableLiveData<String>()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: ""
                    db.collection("users").document(userId).get()
                        .addOnCompleteListener { userTask ->
                            if (userTask.isSuccessful) {
                                _user.value = userTask.result?.toObject(User::class.java)
                                signInResult.value = "Success"
                            } else {
                                signInResult.value = userTask.exception?.message ?: "Sign In Failed"
                            }
                        }
                } else {
                    signInResult.value = task.exception?.message ?: "Sign In Failed"
                }
            }
        return signInResult
    }
}
