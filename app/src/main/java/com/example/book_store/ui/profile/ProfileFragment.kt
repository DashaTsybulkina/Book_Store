package com.example.book_store.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.book_store.R
import com.example.book_store.data.model.Book
import com.example.book_store.data.model.User
import com.example.book_store.databinding.FragmentProfileBinding
import com.example.book_store.getAppComponent
import com.example.book_store.ui.catalog.BookCatalogViewModel

class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null
    val viewModel: ProfileViewModel by viewModels{context?.getAppComponent()!!.profileFactory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view);

        viewModel.user.observe(viewLifecycleOwner, Observer<User> {
            if (it.id == -1) {
                binding!!.buttonSignIn.visibility = View.VISIBLE
                binding!!.llProfile.visibility = View.GONE
            } else {
                binding!!.buttonSignIn.visibility = View.GONE
                binding!!.llProfile.visibility = View.VISIBLE
                binding!!.textEmail.text = it.email
                binding!!.nameText.text = it.firstName+ it.lastName
                binding!!.textPhone.text = it.phone
            }
        })

        binding!!.buttonExit.setOnClickListener(){
            viewModel.deleteUser()
        }
        binding!!.buttonSignIn.setOnClickListener(){
            findNavController().navigate(R.id.loginFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getUser()
    }
}