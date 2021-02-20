package com.kotlin.andi.cinema.ui.home.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kotlin.andi.cinema.BuildConfig
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.domain.model.TV
import com.kotlin.andi.cinema.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.items_movie.view.*

class TVAdapter : PagedListAdapter<TV, TVAdapter.MovieViewHolder>(DIFF_CALLBACK) {

   companion object {
       private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TV>() {
           override fun areItemsTheSame(oldItem: TV, newItem: TV): Boolean {
               return oldItem.id == newItem.id
           }

           override fun areContentsTheSame(oldItem: TV, newItem: TV): Boolean {
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
            holder.bind(movie)
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movies: TV) {
            with(itemView) {
                title_movies.text = movies.name
                rating_movies.rating = movies.voteAverage?.div(2) ?: 0f
                setOnClickListener {
                    val detailIntent = Intent(context, DetailActivity::class.java)
                    detailIntent.putExtra(DetailActivity.EXTRA_TV, movies)
                    context.startActivity(detailIntent)
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
