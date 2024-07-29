package com.example.hirehub.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.hirehub.R

class ProfileDetailFragment : Fragment() {

    private val args: ProfileDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile_detail, container, false)

        val nameTextView: TextView = root.findViewById(R.id.name_text_view)
        val professionTextView: TextView = root.findViewById(R.id.profession_text_view)

        nameTextView.text = args.name
        professionTextView.text = args.profession

        return root
    }
}
