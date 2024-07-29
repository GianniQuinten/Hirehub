package com.example.hirehub.models

import com.google.firebase.Timestamp

data class User(
    val id: String = "",
    val name: String = "",
    val lastname: String = "",
    val dateofbirth: Timestamp? = null,
    val profession: String = "",
    val email: String = "",
    val role: String = "user" // Default role is "user"
)
