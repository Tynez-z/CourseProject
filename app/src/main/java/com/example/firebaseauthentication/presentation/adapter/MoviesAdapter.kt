package com.example.firebaseauthentication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseauthentication.databinding.FragmentItemArticlePreviewBinding
import com.example.firebaseauthentication.R
import com.example.firebaseauthentication.domain.entity.Result

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(private val itemViewBinding: FragmentItemArticlePreviewBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bindView(movieItem: Result) {
            itemViewBinding.apply {
                movie = movieItem

                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(movieItem)
                    }
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Result, newItem: Result) = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArticleViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_item_article_preview, parent, false))

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val movieItem = differ.currentList[position]
        holder.bindView(movieItem)
    }

    override fun getItemCount(): Int =
        differ.currentList.size

    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }
}