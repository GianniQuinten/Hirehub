package com.example.hirehub.ui.masterview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hirehub.models.User
import com.google.firebase.firestore.FirebaseFirestore

class UserListViewModel : ViewModel() {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        db.collection("Users")
            .whereEqualTo("role", "user")
            .get()
            .addOnSuccessListener { documents ->
                val userList = documents.map { document ->
                    document.toObject(User::class.java)
                }
                _users.value = userList
            }
            .addOnFailureListener {
                // Handle failure
            }
    }
}
