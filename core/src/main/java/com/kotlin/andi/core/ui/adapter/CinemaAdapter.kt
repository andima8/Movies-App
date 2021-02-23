package com.kotlin.andi.core.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kotlin.andi.core.BuildConfig
import com.kotlin.andi.core.domain.model.Movies
import com.kotlin.andi.core.R
import kotlinx.android.synthetic.main.items_movie.view.*

class CinemaAdapter(private val onItemClick: (Movies) -> Unit) : PagedListAdapter<Movies, CinemaAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movies>() {
            override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem == newItem
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.items_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie, onItemClick)
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movies: Movies, onItemClick: (Movies) -> Unit) {
            with(itemView) {
                title_movies.text = movies.title
                rating_movies.rating = movies.voteAverage?.div(2) ?: 0f
                itemView.setOnClickListener {
                    onItemClick(movies)
                }
                Glide.with(context)
                    .load(BuildConfig.BASE_IMG_URL + movies.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_movies)
            }
        }
    }
}
