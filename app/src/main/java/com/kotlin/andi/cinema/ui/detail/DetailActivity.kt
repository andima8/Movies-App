package com.kotlin.andi.cinema.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.data.MovieEntity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Mengambil data dari parcelable home
        val data = intent.getParcelableExtra<MovieEntity>(EXTRA_MOVIE)
        Glide.with(this)
            .load(data?.imgMovie)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detail_movie_img)
        detail_movie_genre.text = data?.genreMovie
        tv_rating.text = data?.ratingMovie.toString()
        if (data != null) {
            detail_movie_rating.rating = data.ratingMovie
        }
        detail_movie_desc.text = data?.detailMovie

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitleTextColor(R.style.Collapse_Title)
        setSupportActionBar(toolbar)

        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapsingToolbar.apply {
            title = data?.nameMovie
            setExpandedTitleTextAppearance(R.style.Collapse_Title)
        }
    }
}
