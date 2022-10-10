package com.example.book_store.ui.login

import android.R.attr
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.book_store.R
import com.example.book_store.databinding.FragmentLoginBinding
import com.example.book_store.getAppComponent
import com.example.book_store.ui.registration.RegistrationViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private var binding: FragmentLoginBinding? = null
    val viewModel: LoginViewModel by viewModels{context?.getAppComponent()!!.loginFactory()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance();
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        //updateUI(currentUser)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view);
        binding!!.loginLogin.setOnClickListener() {
            val email = binding!!.loginEditText.text.toString()
            val password = binding!!.passwordEditText.text.toString()
            signIn(email, password)
        }
        binding!!.loginRegistration.setOnClickListener() {
            findNavController().navigate(R.id.registrationFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    fun signIn(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity(),
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Login", "signInWithEmail:success")
                        val user = mAuth.currentUser
                        viewModel.signInUser(email)
                        findNavController().navigate(R.id.profileFragment)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Login", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            context, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
    }

}