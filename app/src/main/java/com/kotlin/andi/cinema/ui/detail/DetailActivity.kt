package com.kotlin.andi.cinema.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.kotlin.andi.cinema.BuildConfig
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.data.MoviesEntity
import com.kotlin.andi.cinema.data.PopularEntity
import com.kotlin.andi.cinema.data.TVEntity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_POPULAR = "extra_popular"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Mengambil data dari parcelable home
        when {
            intent.getParcelableExtra<MoviesEntity>(EXTRA_MOVIE) != null -> {
                val data = intent.getParcelableExtra<MoviesEntity>(EXTRA_MOVIE)
                Glide.with(this)
                    .load(BuildConfig.BASE_IMG_URL + data?.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(detail_movie_img)

                detail_movie_language.text = data?.language
                tv_rating.text = (data?.voteAverage?.div(2)).toString()
                if (data != null) {
                    detail_movie_rating.rating = data.voteAverage?.div(2) ?: 0f
                }
                detail_movie_desc.text = data?.overview

                val toolbar = findViewById<Toolbar>(R.id.toolbar)
                toolbar.setTitleTextColor(R.style.Collapse_Title)
                setSupportActionBar(toolbar)

                val collapsingToolbar =
                    findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
                collapsingToolbar.apply {
                    title = data?.title
                    setExpandedTitleTextAppearance(R.style.Collapse_Title)
                }
            }
            intent.getParcelableExtra<TVEntity>(EXTRA_TV) != null -> {
                val data = intent.getParcelableExtra<TVEntity>(EXTRA_TV)
                Glide.with(this)
                    .load(BuildConfig.BASE_IMG_URL + data?.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(detail_movie_img)

                detail_movie_language.text = data?.language
                tv_rating.text = (data?.voteAverage?.div(2)).toString()
                if (data != null) {
                    detail_movie_rating.rating = data.voteAverage?.div(2) ?: 0f
                }
                detail_movie_desc.text = data?.overview

                val toolbar = findViewById<Toolbar>(R.id.toolbar)
                toolbar.setTitleTextColor(R.style.Collapse_Title)
                setSupportActionBar(toolbar)

                val collapsingToolbar =
                    findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
                collapsingToolbar.apply {
                    title = data?.name
                    setExpandedTitleTextAppearance(R.style.Collapse_Title)
                }
            }
            else -> {
                val data = intent.getParcelableExtra<PopularEntity>(EXTRA_POPULAR)
                Glide.with(this)
                    .load(BuildConfig.BASE_IMG_URL + data?.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(detail_movie_img)

                detail_movie_language.text = data?.language
                tv_rating.text = (data?.voteAverage?.div(2)).toString()
                if (data != null) {
                    detail_movie_rating.rating = data.voteAverage?.div(2) ?: 0f
                }
                detail_movie_desc.text = data?.overview

                val toolbar = findViewById<Toolbar>(R.id.toolbar)
                toolbar.setTitleTextColor(R.style.Collapse_Title)
                setSupportActionBar(toolbar)

                val collapsingToolbar =
                    findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
                collapsingToolbar.apply {
                    title = when {
                        data?.title.isNullOrEmpty() -> {
                            data?.name.toString()
                        }
                        else -> {
                            data?.title.toString()
                        }
                    }
                    setExpandedTitleTextAppearance(R.style.Collapse_Title)
                }
            }
        }
    }
}
