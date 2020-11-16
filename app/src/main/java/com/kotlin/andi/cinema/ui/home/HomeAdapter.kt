package com.kotlin.andi.cinema.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.data.MovieEntity
import com.kotlin.andi.cinema.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.items_movie.view.*

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.MovieViewHolder>()  {
    private  val listMovies = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies.isNullOrEmpty()) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.items_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movies: MovieEntity) {
            with(itemView) {
                title_movies.text = movies.nameMovie
                rating_movies.rating = movies.ratingMovie
                setOnClickListener {
                    val detailIntent = Intent(context, DetailActivity::class.java)
                    detailIntent.putExtra(DetailActivity.EXTRA_MOVIE, movies)
                    context.startActivity(detailIntent)
                }
                Glide.with(context)
                    .load(movies.imgMovie)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(img_movies)
            }
        }
    }
}
