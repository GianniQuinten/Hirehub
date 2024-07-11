package com.example.hirehub.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hirehub.R
import com.example.hirehub.models.Message

class ChatFragment : Fragment() {

    private lateinit var chatViewModel: ChatViewModel
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var messagesRecyclerView: RecyclerView
    private lateinit var messagesAdapter: MessagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_chat, container, false)
        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        messageInput = root.findViewById(R.id.message_input)
        sendButton = root.findViewById(R.id.send_button)
        messagesRecyclerView = root.findViewById(R.id.messages_recycler_view)

        messagesAdapter = MessagesAdapter()
        messagesRecyclerView.layoutManager = LinearLayoutManager(context)
        messagesRecyclerView.adapter = messagesAdapter

        sendButton.setOnClickListener {
            val messageText = messageInput.text.toString()
            if (messageText.isNotEmpty()) {
                chatViewModel.sendMessage(messageText)
                messageInput.text.clear()
            }
        }

        chatViewModel.messages.observe(viewLifecycleOwner, Observer { messages ->
            messagesAdapter.submitList(messages)
            messagesRecyclerView.scrollToPosition(messages.size - 1)
        })

        return root
    }
}
