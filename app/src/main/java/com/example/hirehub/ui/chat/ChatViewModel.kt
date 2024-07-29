package com.example.hirehub.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hirehub.models.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ChatViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> get() = _messages

    init {
        db.collection("Messages")  // Use "Messages" here
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val messageList = snapshot.toObjects(Message::class.java)
                    _messages.value = messageList
                }
            }
    }

    fun sendMessage(text: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val message = Message(
                id = db.collection("Messages").document().id,  // Use "Messages" here
                text = text,
                senderId = userId,
                timestamp = com.google.firebase.Timestamp.now()
            )
            db.collection("Messages").document(message.id).set(message)  // Use "Messages" here
        } else {
            // Handle the case where the user ID is null (not authenticated)
        }
    }
}
