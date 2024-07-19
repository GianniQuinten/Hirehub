package com.example.hirehub.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hirehub.R
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var chatButton: Button
    private lateinit var signOutButton: Button
    private lateinit var viewUsersButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        auth = FirebaseAuth.getInstance()
        chatButton = root.findViewById(R.id.chat_button)
        signOutButton = root.findViewById(R.id.sign_out_button)
        viewUsersButton = root.findViewById(R.id.view_users_button)

        chatButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_chatFragment)
        }

        signOutButton.setOnClickListener {
            auth.signOut()
            Toast.makeText(context, "Signed Out", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFragment_to_mainFragment)
        }

        viewUsersButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_userListFragment)
        }

        return root
    }
}
