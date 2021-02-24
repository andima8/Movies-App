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
import com.kotlin.andi.core.R
import com.kotlin.andi.core.databinding.ItemsMovieBinding
import com.kotlin.andi.core.domain.model.MoviesFav

class CinemaFavAdapter(private val onItemClick: (MoviesFav) -> Unit) : PagedListAdapter<MoviesFav, CinemaFavAdapter.MovieViewHolder>(DIFF_CALLBACK) {

   companion object {
       private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesFav>() {
           override fun areItemsTheSame(
               oldItem: MoviesFav,
               newItem: MoviesFav,
           ): Boolean = oldItem.id == newItem.id

           override fun areContentsTheSame(
               oldItem: MoviesFav,
               newItem: MoviesFav,
           ): Boolean = oldItem == newItem
       }
   }

    fun getSwipedData(swipedPosition: Int): MoviesFav? = getItem(swipedPosition)

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
        private val binding = ItemsMovieBinding.bind(itemView)
        fun bind(movies: MoviesFav, onItemClick: (MoviesFav) -> Unit) {
            with(binding) {
                titleMovies.text = movies.title
                ratingMovies.rating = movies.voteAverage?.div(2) ?: 0f
                    root.setOnClickListener {
                    onItemClick(movies)
                }
                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_IMG_URL + movies.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgMovies)
            }
        }
    }
}
