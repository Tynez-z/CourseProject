package com.example.firebaseauthentication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.firebaseauthentication.R
import com.example.firebaseauthentication.databinding.FragmentDashboardBinding

//TODO BaseFragment
class DashBoardFragment : Fragment() {

    private var binding: FragmentDashboardBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DashBoardFragmentArgs by navArgs()
        binding?.tvNameDashFragment?.text = String.format(
            resources.getString(R.string.user_greet),
            args.name
        )
    }
}