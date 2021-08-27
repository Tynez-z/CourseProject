package com.example.firebaseauthentication.presentation.fragments.login

import android.app.Activity
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.firebaseauthentication.App
import com.example.firebaseauthentication.BR
import com.example.firebaseauthentication.R
import com.example.firebaseauthentication.data.Status
import com.example.firebaseauthentication.databinding.FragmentLoginBinding
import com.example.firebaseauthentication.presentation.fragments.BaseFragment
import com.example.firebaseauthentication.presentation.viewmodel.LoginViewModel
import com.example.firebaseauthentication.utils.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by lazy {
        viewModel {
        }
    }
    lateinit var loginBinding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        loginBinding.setVariable(BR.loginFragment, this)
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivLogoLogin.setOnClickListener {
            createChannel(getString(R.string.channel_id), getString(R.string.channel_name))
        }
//        dialog()

//        //TODO use single fragment with interface
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
                    view.showSnackBar(RESET_EMAIL_SENT)
                } else {
                    view.showSnackBar(it.message.toString())
                }
            }
        }
        dismissBtn.setOnClickListener {
            d.dismiss()
        }

        loginBinding.tvForgotPassword.setOnClickListener {
            d.show()
        }
    }

    fun onClickSignIn() {
        viewModel.signInUser(
            loginBinding.etEmailLogin.text.toString(),
            loginBinding.etPasswordLogin.text.toString()
        ).observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    view?.showSnackBar(LOADING)
                }
                Status.SUCCESS -> {
                    view?.showSnackBar(LOGIN_SUCCESSFUL)
                    if (findNavController().currentDestination?.id == R.id.loginFragment) {
                        NavHostFragment.findNavController(this)
                            .navigate(
                                LoginFragmentDirections.actionLoginFragmentToMoviesFragment(
                                    it.data?.fullName!!
                                )
                            )
                    }
                }
                Status.ERROR -> {
                    view?.showSnackBar(it.message!!)
                }
            }
        })
    }

    fun onClickRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN && resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                viewModel.signInWithGoogle(account!!).observe(viewLifecycleOwner, {
                    if (it.status == Status.SUCCESS) {
                        if (findNavController().currentDestination?.id == R.id.loginFragment) {
                            NavHostFragment.findNavController(this).navigate(
                                LoginFragmentDirections.actionLoginFragmentToMoviesFragment(it?.data?.fullName!!)
                            )
                        }
                    } else if (it.status == Status.ERROR) {
                        requireView().showSnackBar(it.message!!)
                    }
                })
            } catch (e: ApiException) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

//    fun dialog() {
//        val dialog = DialogForgotPassword(requireContext(),
//            object : DialogForgotPassword.forgetPassword {
//                override fun clickOnSend() {
//                    viewModel.sendResetPassword(DialogForgotPassword().etEmailForgot?.text.toString()).observeForever {
//                        if (it.status == Status.SUCCESS) {
//                            view?.showSnackBar(RESET_EMAIL_SENT)
//                        } else {
//                            view?.showSnackBar(it.message.toString())
//                        }
//                    }
//                }
//
//                override fun clickOnDismiss() {
//                }
//            })
//        loginBinding.tvForgotPassword.setOnClickListener {
//            dialog.showDialog()
//        }
//    }

    private fun createChannel(channelID: String, channelName: String) {
        val notificationManager = requireContext().getSystemService(NotificationManager::class.java)
        notificationManager.sendNotification(getString(R.string.message), requireContext())
    }
}