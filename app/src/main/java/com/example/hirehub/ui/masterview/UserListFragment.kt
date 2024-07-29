package com.example.hirehub.ui.masterview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hirehub.R

class UserListFragment : Fragment() {

    private lateinit var userListViewModel: UserListViewModel
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_user_list, container, false)

        userRecyclerView = root.findViewById(R.id.user_recycler_view)
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        userAdapter = UserAdapter()
        userRecyclerView.adapter = userAdapter

        userListViewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        userListViewModel.users.observe(viewLifecycleOwner, Observer { users ->
            users?.let {
                userAdapter.submitList(it)
            }
        })

        return root
    }
}
