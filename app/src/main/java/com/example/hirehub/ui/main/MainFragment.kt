package com.example.hirehub.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hirehub.R

class MainFragment : Fragment() {

    private lateinit var signInButton: Button
    private lateinit var signUpButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        signInButton = root.findViewById(R.id.sign_in_button)
        signUpButton = root.findViewById(R.id.sign_up_button)

        signInButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_signInFragment)
        }

        signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_signUpFragment)
        }

        return root
    }
}
