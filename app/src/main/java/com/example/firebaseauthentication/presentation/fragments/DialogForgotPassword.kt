package com.example.firebaseauthentication.presentation.fragments

import android.app.Dialog
import android.content.Context
import android.view.Window
import androidx.constraintlayout.widget.Constraints
import com.example.firebaseauthentication.R
import kotlinx.android.synthetic.main.forgot_password.*

class DialogForgotPassword constructor(
    private val requireContext: Context,
    private var eventClick: ForgetPassword? = null
) {

    fun showDialog() {

        val dialog = Dialog(requireContext)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(R.layout.forgot_password)
            setCanceledOnTouchOutside(true)
            window?.setLayout(
                Constraints.LayoutParams.MATCH_PARENT,
                Constraints.LayoutParams.WRAP_CONTENT)
            show()
        }

        dialog.btnDismiss.setOnClickListener {
            eventClick?.clickOnDismiss()
            dialog.dismiss()
        }

        dialog.btnSendEmail.setOnClickListener {
            eventClick?.clickOnSend(dialog.etEmailForgot.text.toString())
        }
    }

    interface ForgetPassword {
        fun clickOnDismiss()
        fun clickOnSend(email: String)
    }
}