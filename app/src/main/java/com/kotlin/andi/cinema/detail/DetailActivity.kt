package com.kotlin.andi.cinema.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.kotlin.andi.cinema.BuildConfig
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.databinding.ActivityDetailBinding
import com.kotlin.andi.core.domain.model.Movies
import com.kotlin.andi.core.domain.model.MoviesFav
import com.kotlin.andi.core.domain.model.TV
import com.kotlin.andi.core.domain.model.TVFav
import com.kotlin.andi.core.ui.viewmodel.DetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_FAV_MOVIE = "extra_fav_movie"
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_FAV_TV = "extra_fav_tv"
    }

    private lateinit var moviesFav: MoviesFav
    private lateinit var tvFav: TVFav
    private val detailViewModel: DetailViewModel by viewModel()
    private var stateFav: Boolean = false
    private lateinit var dataMovies: Movies
    private lateinit var movieId: String
    private lateinit var dataTV: TV
    private lateinit var tvId: String
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Mengambil data dari parcelable home
        when {
            intent.getParcelableExtra<Movies>(EXTRA_MOVIE) != null -> {
                dataMovies = intent.getParcelableExtra(EXTRA_MOVIE) ?: dataMovies
                movieId = dataMovies.id.toString()
                initObserver()
                setFavorites()
                binding.fabFavorite.setOnClickListener {
                    if (stateFav) removeFavorite()
                    else addToMoviesFavorites()
                    stateFav = !stateFav
                    setFavorites()
                }
                getDataMovies(dataMovies)
            }
            intent.getParcelableExtra<MoviesFav>(EXTRA_FAV_MOVIE) != null -> {
                moviesFav = intent.getParcelableExtra(EXTRA_FAV_MOVIE) ?: moviesFav
                movieId = moviesFav.movieId.toString()
                initObserver()
                setFavorites()
                binding.fabFavorite.setOnClickListener {
                    if (stateFav) removeFavorite()
                    else addToMoviesFavorites()
                    stateFav = !stateFav
                    setFavorites()
                }
                getDataFavMovies(moviesFav)
            }
            intent.getParcelableExtra<TV>(EXTRA_TV) != null -> {
                dataTV = intent.getParcelableExtra(EXTRA_TV) ?: dataTV
                tvId = dataTV.id.toString()
                initObserver()
                setFavorites()
                binding.fabFavorite.setOnClickListener {
                    if (stateFav) removeFavorite()
                    else addToTVFavorites()
                    stateFav = !stateFav
                    setFavorites()
                }
                getDataTV(dataTV)
            }
            intent.getParcelableExtra<TVFav>(EXTRA_FAV_TV) != null -> {
                tvFav = intent.getParcelableExtra(EXTRA_FAV_TV) ?: tvFav
                tvId = tvFav.tvId.toString()
                initObserver()
                setFavorites()
                binding.fabFavorite.setOnClickListener {
                    if (stateFav) removeFavorite()
                    else addToTVFavorites()
                    stateFav = !stateFav
                    setFavorites()
                }
                getDataFavTV(tvFav)
            }
        }
    }

    private fun initObserver() {
        when {
            intent.getParcelableExtra<Movies>(EXTRA_MOVIE) != null ||
                    intent.getParcelableExtra<MoviesFav>(EXTRA_FAV_MOVIE) != null ->
                detailViewModel.checkFavMovies(movieId).asLiveData().observe(this, moviesObserver)

            intent.getParcelableExtra<TV>(EXTRA_TV) != null ||
                    intent.getParcelableExtra<TVFav>(EXTRA_FAV_TV) != null ->
                detailViewModel.checkFavTV(tvId).asLiveData().observe(this, tvObserver)
        }
    }

    private fun addToTVFavorites() {
        when {
            intent.getParcelableExtra<TV>(EXTRA_TV) != null -> {
                val tvId = dataTV.id.toString()
                val overview = dataTV.overview
                val poster = dataTV.posterPath
                val backdrop = dataTV.backdropPath
                val nameTVShows = dataTV.name
                val vote = dataTV.voteAverage
                val language = dataTV.language
                val inputTV =
                    TVFav(
                        0,
                        tvId,
                        overview,
                        poster,
                        backdrop,
                        nameTVShows,
                        vote,
                        language
                    )
                detailViewModel.addTVFav(inputTV)
                Toast.makeText(this, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
            }
            intent.getParcelableExtra<TVFav>(EXTRA_FAV_TV) != null -> {
                val tvId = tvFav.tvId.toString()
                val overview = tvFav.overview
                val poster = tvFav.posterPath
                val backdrop = tvFav.backdropPath
                val nameTVShows = tvFav.name
                val vote = tvFav.voteAverage
                val language = tvFav.language
                val inputTV =
                    TVFav(
                        0,
                        tvId,
                        overview,
                        poster,
                        backdrop,
                        nameTVShows,
                        vote,
                        language
                    )
                detailViewModel.addTVFav(inputTV)
                Toast.makeText(this, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val moviesObserver = Observer<List<MoviesFav>> { movies ->
        if (movies.isEmpty()) {
            stateFav = false
            setFavorites()
        } else {
            moviesFav = movies.first()
            stateFav = true
            setFavorites()
        }
    }

    private val tvObserver = Observer<List<TVFav>> { tv ->
        if (tv.isEmpty()) {
            stateFav = false
            setFavorites()
        } else {
            tvFav = tv.first()
            stateFav = true
            setFavorites()
        }
    }

    private fun setFavorites() {
        if (stateFav) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_on)
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_off)
            )
        }
    }

    private fun addToMoviesFavorites() {
        when {
            intent.getParcelableExtra<Movies>(EXTRA_MOVIE) != null -> {
                val movieId = dataMovies.id.toString()
                val overview = dataMovies.overview
                val poster = dataMovies.posterPath
                val backdrop = dataMovies.backdropPath
                val nameMovie = dataMovies.name
                val titleMovie = dataMovies.title
                val vote = dataMovies.voteAverage
                val language = dataMovies.language
                val inputMovies =
                    MoviesFav(
                        0,
                        movieId,
                        overview,
                        poster,
                        backdrop,
                        nameMovie,
                        titleMovie,
                        vote,
                        language
                    )
                detailViewModel.addMoviesFav(inputMovies)
                Toast.makeText(this, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
            }
            intent.getParcelableExtra<MoviesFav>(EXTRA_FAV_MOVIE) != null -> {
                val movieId = moviesFav.movieId.toString()
                val overview = moviesFav.overview
                val poster = moviesFav.posterPath
                val backdrop = moviesFav.backdropPath
                val nameMovie = moviesFav.name
                val titleMovie = moviesFav.title
                val vote = moviesFav.voteAverage
                val language = moviesFav.language
                val inputMovies =
                    MoviesFav(
                        0,
                        movieId,
                        overview,
                        poster,
                        backdrop,
                        nameMovie,
                        titleMovie,
                        vote,
                        language
                    )
                detailViewModel.addMoviesFav(inputMovies)
                Toast.makeText(this, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun removeFavorite() {
        if (intent.getParcelableExtra<MoviesFav>(EXTRA_FAV_MOVIE) != null ||
            intent.getParcelableExtra<Movies>(EXTRA_MOVIE) != null
        ) {
            moviesFav.let {
                detailViewModel.deleteMoviesFav(it)
                detailViewModel.checkFavMovies(it.movieId ?: "")
            }
        } else if (intent.getParcelableExtra<TVFav>(EXTRA_FAV_TV) != null ||
            intent.getParcelableExtra<TV>(EXTRA_TV) != null
        ) {
            tvFav.let {
                detailViewModel.deleteTVFav(it)
                detailViewModel.checkFavTV(it.tvId ?: "")
            }
        }
        Toast.makeText(this, getString(R.string.delete_favorite), Toast.LENGTH_SHORT).show()
    }

    private fun getDataMovies(data: Movies?) {
        with(binding) {
            Glide.with(applicationContext)
                .load(BuildConfig.BASE_IMG_URL + data?.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(detailMovieImg)

            detailMovieLanguage.text = data?.language
            tvRating.text = (data?.voteAverage?.div(2)).toString()
            if (data != null) {
                detailMovieRating.rating = data.voteAverage?.div(2) ?: 0f
            }
            detailMovieDesc.text = data?.overview

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
    }

    private fun getDataFavMovies(data: MoviesFav?) {
        with(binding) {
            Glide.with(applicationContext)
                .load(BuildConfig.BASE_IMG_URL + data?.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(detailMovieImg)

            detailMovieLanguage.text = data?.language
            tvRating.text = (data?.voteAverage?.div(2)).toString()
            if (data != null) {
                detailMovieRating.rating = data.voteAverage?.div(2) ?: 0f
            }
            detailMovieDesc.text = data?.overview

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
    }

    private fun getDataTV(data: TV?) {
        with(binding) {
            Glide.with(applicationContext)
                .load(BuildConfig.BASE_IMG_URL + data?.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(detailMovieImg)

            detailMovieLanguage.text = data?.language
            tvRating.text = (data?.voteAverage?.div(2)).toString()
            if (data != null) {
                detailMovieRating.rating = data.voteAverage?.div(2) ?: 0f
            }
            detailMovieDesc.text = data?.overview

            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            toolbar.setTitleTextColor(R.style.Collapse_Title)
            setSupportActionBar(toolbar)

            val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
            collapsingToolbar.apply {
                title = data?.name
                setExpandedTitleTextAppearance(R.style.Collapse_Title)
            }
        }
    }

    private fun getDataFavTV(data: TVFav?) {
        with(binding) {
            Glide.with(applicationContext)
                .load(BuildConfig.BASE_IMG_URL + data?.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(detailMovieImg)

            detailMovieLanguage.text = data?.language
            tvRating.text = (data?.voteAverage?.div(2)).toString()
            if (data != null) {
                detailMovieRating.rating = data.voteAverage?.div(2) ?: 0f
            }
            detailMovieDesc.text = data?.overview

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
    }
}
