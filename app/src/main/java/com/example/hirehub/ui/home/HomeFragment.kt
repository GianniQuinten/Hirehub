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

        val signUpButton: Button = root.findViewById(R.id.sign_up_button)
        val signInButton: Button = root.findViewById(R.id.sign_in_button)

        signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_signUpFragment)
        }

        signInButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_signInFragment)
        }

        return root
    }
}
