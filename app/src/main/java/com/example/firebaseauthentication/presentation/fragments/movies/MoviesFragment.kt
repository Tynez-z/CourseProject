package com.example.firebaseauthentication.presentation.fragments.movies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.firebaseauthentication.BR
import com.example.firebaseauthentication.MainActivity
import com.example.firebaseauthentication.R
import com.example.firebaseauthentication.databinding.FragmentMoviesBinding
import com.example.firebaseauthentication.presentation.adapter.MoviesAdapter
import com.example.firebaseauthentication.presentation.fragments.BaseFragment
import com.example.firebaseauthentication.presentation.viewmodel.MoviesViewModel
import com.example.firebaseauthentication.utils.ERROR
import com.example.firebaseauthentication.utils.RESULT_NAV
import com.example.firebaseauthentication.utils.TAG
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MoviesFragment : BaseFragment(R.layout.fragment_movies) {

    private val moviesAdapter = MoviesAdapter()
    lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    private val viewModel: MoviesViewModel by lazy {
        viewModel {
            //TODO add logic in future (LifecycleOwner)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMoviesBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        fragmentMoviesBinding.setVariable(BR.moviesFragment, this)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: MoviesFragmentArgs by navArgs()
        fragmentMoviesBinding.tvNameUser.text =
            String.format(resources.getString(R.string.user_greet), args.name)

        setupRecyclerView()
        getMovies()
        getMoviesErrorState()
        showDescribeMovie()
    }

    private fun getMoviesErrorState() {
        viewModel.errorStateLiveData.observe(viewLifecycleOwner, { error ->
            //TODO do not use Logs, it is not security
            Log.e(TAG, ERROR + error)
        })
    }

    private fun getMovies() {
        viewModel.moviesNews.observe(viewLifecycleOwner, { response ->
            moviesAdapter.differ.submitList(response)
        })
    }

    private fun setupRecyclerView() {
        fragmentMoviesBinding.rvMovies.adapter = moviesAdapter
    }

    private fun showDescribeMovie() {
        moviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable(RESULT_NAV, it)
            }
            findNavController().navigate(R.id.action_moviesFragment_to_articleFragment, bundle)
        }
    }

    fun onClickGoBack() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        val googleSignInClient = GoogleSignIn.getClient(context!!, gso)
        googleSignInClient.signOut()

        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }
}