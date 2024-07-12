package com.example.hirehub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.findNavController()

        if (auth.currentUser == null) {
            // User is not signed in, navigate to the main screen with sign-in and sign-up options
            navController.navigate(R.id.mainFragment)
        } else {
            // User is signed in, navigate to the home screen
            navController.navigate(R.id.homeFragment)
        }
    }
}
