package com.example.hirehub.ui.signin

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
import androidx.navigation.fragment.findNavController
import com.example.hirehub.R

class SignInFragment : Fragment() {

    private lateinit var signInViewModel: SignInViewModel
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_signin, container, false)
        signInViewModel = ViewModelProvider(this).get(SignInViewModel::class.java)

        emailEditText = root.findViewById(R.id.email)
        passwordEditText = root.findViewById(R.id.password)
        signInButton = root.findViewById(R.id.sign_in_button)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            signInViewModel.signIn(email, password).observe(viewLifecycleOwner, Observer { result ->
                if (result == "Success") {
                    Toast.makeText(context, "Sign In Successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
                } else {
                    Toast.makeText(context, "Sign In Failed: $result", Toast.LENGTH_SHORT).show()
                }
            })
        }

        return root
    }
}
