package com.example.firebaseauthentication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.firebaseauthentication.R
import com.example.firebaseauthentication.data.Status
import com.example.firebaseauthentication.databinding.FragmentSignUpBinding
import com.example.firebaseauthentication.ui.viewmodel.SignUpViewModel
import com.example.firebaseauthentication.utils.    showSnackBar
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private val viewModel: SignUpViewModel by lazy {
        viewModel {
        }
    }
    private var binding: FragmentSignUpBinding? = null

    @Inject
    lateinit var auth: FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding?.root
    }
//TODO constants

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.signUpBtn?.setOnClickListener {
            val emailText = binding?.etEmailSignUp?.text?.toString()
            val passwordText = binding?.etPasswordSignUp?.text.toString()
            val fullNameText = binding?.etNameUser?.text?.toString()
            viewModel.signUpUser(emailText.toString(), passwordText, fullNameText.toString())
                .observe(viewLifecycleOwner, {
                    when (it.status) {
                        Status.SUCCESS -> {
                            viewModel.saveUser(
                                it.data?.email.toString(),
                                it.data?.fullName.toString()
                            )
                            view.showSnackBar("User account registered")
                        }
                        Status.ERROR -> {
                            view.showSnackBar(it.message!!)
                        }
                        Status.LOADING -> {
                            view.showSnackBar("...")
                        }
                    }
                })
        }
        binding?.tvLoginSignUp?.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.signUpFragment) {
                NavHostFragment.findNavController(this)
                    .navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
            }
        }
    }
}