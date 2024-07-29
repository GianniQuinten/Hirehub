package com.example.hirehub.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hirehub.models.User
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpViewModel : ViewModel() {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    fun signUp(email: String, password: String, name: String, lastname: String, dateofbirth: Timestamp?, profession: String): LiveData<String> {
        val signUpResult = MutableLiveData<String>()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: ""
                    val user = User(
                        id = userId,
                        name = name,
                        lastname = lastname,
                        dateofbirth = dateofbirth,
                        profession = profession,
                        email = email,
                        role = "user" // Default role is "user"
                    )
                    db.collection("Users").document(userId).set(user)  // Use "Users" here
                        .addOnCompleteListener { userTask ->
                            if (userTask.isSuccessful) {
                                signUpResult.value = "Success"
                            } else {
                                signUpResult.value = userTask.exception?.message ?: "Sign Up Failed"
                            }
                        }
                } else {
                    signUpResult.value = task.exception?.message ?: "Sign Up Failed"
                }
            }
        return signUpResult
    }
}
