package com.example.hirehub.models

import com.google.firebase.Timestamp

data class Message(
    val id: String = "",
    val text: String = "",
    val senderId: String = "",
    val timestamp: Timestamp = Timestamp.now()
)


