package com.example.firebaseauthentication.presentation.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.firebaseauthentication.R
import com.example.firebaseauthentication.data.Status
import com.example.firebaseauthentication.databinding.FragmentSignUpBinding
import com.example.firebaseauthentication.presentation.fragments.BaseFragment
import com.example.firebaseauthentication.presentation.viewmodel.SignUpViewModel
import com.example.firebaseauthentication.utils.LOADING
import com.example.firebaseauthentication.utils.USER_REGISTERED
import com.example.firebaseauthentication.utils.showSnackBar
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private val viewModel: SignUpViewModel by lazy {
        viewModel {
        }
    }
    lateinit var signUpBinding: FragmentSignUpBinding

    @Inject
    lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        signUpBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        signUpBinding.setVariable(BR.signUpFragment, this)
        return signUpBinding.root
    }

    fun onClickGoToLogin() {
        if (findNavController().currentDestination?.id == R.id.signUpFragment) {
            NavHostFragment.findNavController(this)
                .navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
        }
    }

    fun onCLickSignUp() {
        signUpBinding.apply {
            viewModel.signUpUser(
                signUpBinding.etEmailSignUp.text.toString(),
                signUpBinding.etPasswordSignUp.text.toString(),
                signUpBinding.etNameUser.text.toString()
            ).observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.SUCCESS -> {
                        viewModel.saveUser(
                            it.data?.email.toString(),
                            it.data?.fullName.toString()
                        )
                        view?.showSnackBar(USER_REGISTERED)
                    }
                    Status.ERROR -> {
                        view?.showSnackBar(it.message!!) //TODO add check NPE
                    }
                    Status.LOADING -> {
                        view?.showSnackBar(LOADING)
                    }
                }
            })
        }
    }
}