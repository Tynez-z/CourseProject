package com.example.firebaseauthentication.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.firebaseauthentication.R
import com.example.firebaseauthentication.data.Status
import com.example.firebaseauthentication.databinding.FragmentLoginBinding
import com.example.firebaseauthentication.ui.viewmodel.LoginViewModel
import com.example.firebaseauthentication.utils.showSnackBar

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by lazy {
        viewModel {
        }
    }
    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.tvSignUp?.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.loginFragment) {
                NavHostFragment.findNavController(this)
                    .navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
            }
        }

        //TODO extension fun navHostFragment
        //TODO use click xml
        //TODO constants
        binding?.btnSignIn?.setOnClickListener {
            viewModel.signInUser(
                binding?.etEmailLogin?.text.toString(),
                binding?.etPasswordLogin?.text.toString()
            ).observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.LOADING -> {
                        view.showSnackBar("...")
                    }
                    Status.SUCCESS -> {
                        view.showSnackBar("Login successful")
                        if (findNavController().currentDestination?.id == R.id.loginFragment) {
                            NavHostFragment.findNavController(this)
                                .navigate(LoginFragmentDirections.actionLoginFragmentToDashBoardFragment(
                                        it.data?.fullName!!
                                    )
                                )
                        }
                    }
                    Status.ERROR -> {
                        view.showSnackBar(it.message!!)
                    }
                }
            })
        }

        //forget password
        val dialog = AlertDialog.Builder(requireContext())
        val inflater = (requireActivity()).layoutInflater
        val v = inflater.inflate(R.layout.forgot_password, null)
        dialog.setView(v).setCancelable(false)
        val d = dialog.create()
        val sendBtn = v.findViewById<AppCompatButton>(R.id.btnSendEmail)
        val dismissBtn = v.findViewById<AppCompatButton>(R.id.btnDismiss)
        val etEmailForgot = v.findViewById<EditText>(R.id.etEmailForgot)

        sendBtn.setOnClickListener {
            viewModel.sendResetPassword(etEmailForgot.text.toString()).observeForever {
                if (it.status == Status.SUCCESS) {
                    view.showSnackBar("reset email sent")
                } else {
                    view.showSnackBar(it.message.toString())
                }
            }
        }
        dismissBtn.setOnClickListener {
            d.dismiss()
        }

        binding?.tvForgotPassword?.setOnClickListener {
            d.show()
        }
    }
}