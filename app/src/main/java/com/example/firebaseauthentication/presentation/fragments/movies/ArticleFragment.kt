package com.example.firebaseauthentication.presentation.fragments.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.firebaseauthentication.BR
import com.example.firebaseauthentication.R
import com.example.firebaseauthentication.databinding.FragmentArticleBinding
import com.example.firebaseauthentication.presentation.fragments.BaseFragment
import com.example.firebaseauthentication.presentation.viewmodel.ArticleViewModel

class ArticleFragment : BaseFragment(R.layout.fragment_article) {

    private val args: ArticleFragmentArgs by navArgs()
    private lateinit var fragmentArticleBinding: FragmentArticleBinding

    private val viewModel: ArticleViewModel by lazy {
        viewModel {
            //TODO add logic in future (LifecycleOwner)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentArticleBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        fragmentArticleBinding.apply {
            setVariable(BR.articleFragment, this)
            setVariable(BR.movie, args.result)
        }
        return fragmentArticleBinding.root
    }

    fun saveMovies() {
        args.result.let { result ->
            fragmentArticleBinding.movie = result
            viewModel.saveArticle(result)
        }
    }
}