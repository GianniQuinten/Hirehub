package com.example.hirehub.ui.signup

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
import com.example.hirehub.R

class SignUpFragment : Fragment() {

    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_signup, container, false)
        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        emailEditText = root.findViewById(R.id.email)
        passwordEditText = root.findViewById(R.id.password)
        signUpButton = root.findViewById(R.id.sign_up_button)

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            signUpViewModel.signUp(email, password).observe(viewLifecycleOwner, Observer { result ->
                if (result == "Success") {
                    Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Sign Up Failed: $result", Toast.LENGTH_SHORT).show()
                }
            })
        }

        return root
    }
}
