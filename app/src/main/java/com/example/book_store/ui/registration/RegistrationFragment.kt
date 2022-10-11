package com.example.book_store.ui.registration

import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.book_store.R
import com.example.book_store.databinding.FragmentRegistrationBinding
import com.example.book_store.getAppComponent
import com.example.book_store.ui.profile.ProfileViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_registration.*


class RegistrationFragment : Fragment() {
    lateinit var mAuth: FirebaseAuth
    private var binding: FragmentRegistrationBinding? = null
    val viewModel: RegistrationViewModel by viewModels{context?.getAppComponent()!!.registrationFactory()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth!!.currentUser
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view);
        binding!!.registrationRegistration.setOnClickListener(){
            val email = binding!!.registrationEmailInput.text.toString()
            val password = binding!!.registrationPasswordInput.text.toString()
            val firstname =
                binding!!.registrationFirstnameInput.text.toString()
            val lastname = binding!!.registrationLastnameInput.text.toString()
            val phone = registration_phone_input.text.toString()
            val passwordConfirm =
                binding!!.registrationPasswordInputConfirm.text.toString()
            if(checkData(password, passwordConfirm, phone, email)){
                registration(email, password, phone, firstname, lastname)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    private fun checkData(password:String, passwordConfirm:String, phone:String, email: String):Boolean{
        if (password == passwordConfirm && password.length >= 8) {
            if (phone.length == "+375293940577".length) {
                return true
            } else {
                Toast.makeText(
                    context,
                    "Введите телефон формата +375290000000",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        } else {
            Toast.makeText(
                context,
                "Пароли не совпадают или меньше 8 символов",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
    }

    fun registration(email: String, password: String, phone: String, firstName:String, lastName:String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                requireActivity(),
                OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser
                        viewModel.registeringUser(email, phone, firstName, lastName)
                        findNavController().navigate(R.id.profileFragment)
                    } else {
                        Toast.makeText(
                            context, "Ошибка регистрации",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
    }
}