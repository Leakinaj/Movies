package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.model.Result
import com.example.movies.databinding.ItemPostBinding
import com.squareup.picasso.Picasso

class MovieAdapter(var list: List<Result>? = null,
) : RecyclerView.Adapter<MovieAdapter.FilmViewHolder>() {
    class FilmViewHolder(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root)

    var itemClickListener: RecyclerViewItemClick? = null
    val listFilm = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding: ItemPostBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_post,
                parent,
                false
            )
        return FilmViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                    parent,
                    false
            )
        )
    }

    override fun getItemCount(): Int = list?.size?: 0

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        with(holder.binding) {
            val movie = listFilm?.get(position)
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie?.poster_path)
                .into(imView);
            tvTitle.text = movie?.title
            root.setOnClickListener {
                itemClickListener?.itemClick(movie!!.id)
            }
        }
    }

    interface RecyclerViewItemClick {
        fun itemClick(movieId: Int)
    }
}

//    private val diffCallback = object : DiffUtil.ItemCallback<Film>() {
//        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
//            return oldItem == newItem
//        }
//
//        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    private val differ = AsyncListDiffer(this, diffCallback)
//
//    fun submitList(list: List<Film>?) {
//        differ.submitList(list)
//    }
//
//    fun clearAll() {
//        differ.submitList(null)
//    }