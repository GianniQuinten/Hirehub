package com.example.hirehub.ui.signup

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hirehub.R
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SignUpFragment : Fragment() {

    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var nameEditText: EditText
    private lateinit var lastnameEditText: EditText
    private lateinit var dateOfBirthTextView: TextView
    private lateinit var professionEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button

    private var selectedDateOfBirth: Timestamp? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_signup, container, false)
        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        nameEditText = root.findViewById(R.id.name)
        lastnameEditText = root.findViewById(R.id.lastname)
        dateOfBirthTextView = root.findViewById(R.id.dateofbirth)
        professionEditText = root.findViewById(R.id.profession)
        emailEditText = root.findViewById(R.id.email)
        passwordEditText = root.findViewById(R.id.password)
        signUpButton = root.findViewById(R.id.sign_up_button)

        // Set up DatePickerDialog
        dateOfBirthTextView.setOnClickListener {
            showDatePickerDialog()
        }

        signUpButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val lastname = lastnameEditText.text.toString()
            val dateOfBirth = selectedDateOfBirth
            val profession = professionEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            signUpViewModel.signUp(email, password, name, lastname, dateOfBirth, profession).observe(viewLifecycleOwner, Observer { result ->
                if (result == "Success") {
                    Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                    // Navigate to the next screen
                } else {
                    Toast.makeText(context, "Sign Up Failed: $result", Toast.LENGTH_SHORT).show()
                }
            })
        }

        return root
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(selectedYear, selectedMonth, selectedDay)
            selectedDateOfBirth = Timestamp(selectedDate.time)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            dateOfBirthTextView.text = dateFormat.format(selectedDate.time)
        }, year, month, day)

        datePickerDialog.show()
    }
}
