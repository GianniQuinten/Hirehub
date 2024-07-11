package com.example.hirehub.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hirehub.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val chatButton: Button = root.findViewById(R.id.chat_button)

        chatButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_chatFragment)
        }

        return root
    }
}
