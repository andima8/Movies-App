package com.kotlin.andi.core.ui.adapter

import android.content.Intent
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
import com.kotlin.andi.core.domain.model.TVFav
import kotlinx.android.synthetic.main.items_movie.view.*

class TvShowsFavAdapter(private val onItemClick: (TVFav) -> Unit) : PagedListAdapter<TVFav, TvShowsFavAdapter.TvViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVFav>() {
            override fun areItemsTheSame(
                oldItem: TVFav,
                newItem: TVFav
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: TVFav,
                newItem: TVFav
            ): Boolean = oldItem == newItem
        }
    }

    fun getSwipedData(swipedPosition: Int): TVFav? = getItem(swipedPosition)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.items_movie, parent, false)
        return TvViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv, onItemClick)
        }
    }

    class TvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movies: TVFav, onItemClick: (TVFav) -> Unit) {
            with(itemView) {
                title_movies.text = movies.name
                rating_movies.rating = movies.voteAverage?.div(2) ?: 0f
                setOnClickListener {
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
