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
import android.text.InputType
import android.widget.ImageView

class SignInFragment : Fragment() {

    private lateinit var signInViewModel: SignInViewModel
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: Button
    private lateinit var passwordToggle: ImageView
    private var isPasswordVisible: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_signin, container, false)
        signInViewModel = ViewModelProvider(this).get(SignInViewModel::class.java)

        emailEditText = root.findViewById(R.id.email)
        passwordEditText = root.findViewById(R.id.password)
        passwordToggle = root.findViewById(R.id.password_toggle)
        signInButton = root.findViewById(R.id.sign_in_button)

        // Handle password visibility toggle
        passwordToggle.setOnClickListener {
            togglePasswordVisibility()
        }

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            signInViewModel.signIn(email, password).observe(viewLifecycleOwner, Observer { result ->
                if (result == "Success") {
                    Toast.makeText(context, "Sign In Successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
                } else {
                    Toast.makeText(context, "Sign In Failed: $result", Toast.LENGTH_SHORT).show()
                }
            })
        }

        return root
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide the password
            passwordEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            passwordToggle.setImageResource(R.drawable.ic_visibility_off)
        } else {
            // Show the password
            passwordEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            passwordToggle.setImageResource(R.drawable.ic_visibility)
        }

        // Move cursor to the end after toggling
        passwordEditText.setSelection(passwordEditText.text.length)

        // Toggle the password visibility flag
        isPasswordVisible = !isPasswordVisible
    }
}
