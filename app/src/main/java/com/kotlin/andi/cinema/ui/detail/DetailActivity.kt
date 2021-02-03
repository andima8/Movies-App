package com.kotlin.andi.cinema.ui.detail

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.kotlin.andi.cinema.BuildConfig
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.data.source.local.entity.MoviesEntity
import com.kotlin.andi.cinema.data.source.local.entity.TVEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.TVFavEntity
import com.kotlin.andi.cinema.viewmodel.MovieViewModel
import com.kotlin.andi.cinema.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_FAV_MOVIE = "extra_fav_movie"
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_FAV_TV = "extra_fav_tv"
    }

    private lateinit var moviesFavEntity: MoviesFavEntity
    private lateinit var tvFavEntity: TVFavEntity
    private lateinit var movieViewModel: MovieViewModel
    private var stateFav: Boolean = false
    private lateinit var dataMovies: MoviesEntity
    private lateinit var movieId: String
    private lateinit var dataTV: TVEntity
    private lateinit var tvId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        // Mengambil data dari parcelable home
        movieViewModel = obtainViewModel(this)
        when {
            intent.getParcelableExtra<MoviesEntity>(EXTRA_MOVIE) != null -> {
                dataMovies = intent.getParcelableExtra(EXTRA_MOVIE) ?: return
                moviesDetail(dataMovies)
            }
            intent.getParcelableExtra<MoviesFavEntity>(EXTRA_FAV_MOVIE) != null -> {
                moviesFavEntity = intent.getParcelableExtra(EXTRA_FAV_MOVIE) ?: return
                moviesFavDetail(moviesFavEntity)
            }
            intent.getParcelableExtra<TVEntity>(EXTRA_TV) != null -> {
                dataTV = intent.getParcelableExtra(EXTRA_TV) ?: return
                tvDetail(dataTV)
            }
            intent.getParcelableExtra<TVFavEntity>(EXTRA_FAV_TV) != null -> {
                tvFavEntity = intent.getParcelableExtra(EXTRA_FAV_TV) ?: return
                tvFavDetail(tvFavEntity)
            }
        }
    }

    private fun tvDetail(data: TVEntity?) {
        tvId = data?.id.toString()
        initTVObserver()
        setFavorites()
        getDataTV(data)
        fab_favorite.setOnClickListener {
            if (stateFav) removeFavTVShows()
            else addTVtoFavorites()
            stateFav = !stateFav
            setFavorites()
        }
    }

    private fun tvFavDetail(data: TVFavEntity?) {
        tvId = data?.id.toString()
        stateFav = true
        setFavorites()
        getDataFavTV(data)
        fab_favorite.setOnClickListener {
            if (stateFav) removeFavTVShows()
            else addMoviesToFavorites()
            stateFav = !stateFav
            setFavorites()
        }
    }

    private fun moviesFavDetail(data: MoviesFavEntity?) {
        movieId = data?.id.toString()
        stateFav = true
        setFavorites()
        getDataFavMovies(data)
        fab_favorite.setOnClickListener {
            if (stateFav) removeFavMovies()
            else addMoviesToFavorites()
            stateFav = !stateFav
            setFavorites()
        }
    }

    private fun moviesDetail(data: MoviesEntity?) {
        movieId = data?.id.toString()
        getDataMovies(data)
        initMovieObserver()
        setFavorites()
        fab_favorite.setOnClickListener {
            if (stateFav) removeFavMovies()
            else addMoviesToFavorites()
            stateFav = !stateFav
            setFavorites()
        }
    }

    private fun initMovieObserver() {
        movieViewModel.checkFavMovies(movieId).observe(this, moviesObserver)
    }

    private fun initTVObserver() {
        movieViewModel.checkFavTV(tvId).observe(this, tvObserver)
    }

    private val moviesObserver = Observer<List<MoviesFavEntity>> { movies ->
        if (movies != null) {
            handleFavMovieDataFromDB(movies)
        }
    }

    private val tvObserver = Observer<List<TVFavEntity>> { tv ->
        if (tv != null) {
            handleFavTVDataFromDB(tv)
        }
    }

    private fun handleFavMovieDataFromDB(movies: List<MoviesFavEntity>) {
        if (movies.isEmpty()) {
            stateFav = false
            setFavorites()
        } else {
            moviesFavEntity = movies.first()
            stateFav = true
            setFavorites()
        }
    }

    private fun handleFavTVDataFromDB(tv: List<TVFavEntity>) {
        if (tv.isEmpty()) {
            stateFav = false
            setFavorites()
        } else {
            tvFavEntity = tv.first()
            stateFav = true
            setFavorites()
        }
    }

    private fun setFavorites() {
        if (stateFav) {
            fab_favorite.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_on)
            )
        } else {
            fab_favorite.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_off)
            )
        }
    }

    private fun obtainViewModel(context: Context): MovieViewModel {
        val factory = ViewModelFactory.getInstance(context)
        return ViewModelProvider(this, factory).get(MovieViewModel::class.java)
    }

    private fun addMoviesToFavorites() {
        when {
            intent.getParcelableExtra<MoviesEntity>(EXTRA_MOVIE) != null -> {
                val movieId = dataMovies.id.toString()
                val overview = dataMovies.overview
                val poster = dataMovies.posterPath
                val backdrop = dataMovies.backdropPath
                val nameMovie = dataMovies.name
                val titleMovie = dataMovies.title
                val vote = dataMovies.voteAverage
                val language = dataMovies.language
                val inputMoviesEntity =
                    MoviesFavEntity(
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
                movieViewModel.addMoviesFav(inputMoviesEntity)
                Toast.makeText(this, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
            }
            intent.getParcelableExtra<MoviesFavEntity>(EXTRA_FAV_MOVIE) != null -> {
                val movieId = moviesFavEntity.id.toString()
                val overview = moviesFavEntity.overview
                val poster = moviesFavEntity.posterPath
                val backdrop = moviesFavEntity.backdropPath
                val nameMovie = moviesFavEntity.name
                val titleMovie = moviesFavEntity.title
                val vote = moviesFavEntity.voteAverage
                val language = moviesFavEntity.language
                val inputMoviesEntity =
                    MoviesFavEntity(
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
                movieViewModel.addMoviesFav(inputMoviesEntity)
                Toast.makeText(this, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun removeFavMovies() {
        movieViewModel = obtainViewModel(this)
        moviesFavEntity.let {
            movieViewModel.deleteMoviesFav(it)
            movieViewModel.checkFavMovies(it.movieId ?: "")
        }
        Toast.makeText(this, getString(R.string.delete_favorite), Toast.LENGTH_SHORT).show()
    }

    private fun addTVtoFavorites() {
        when {
            intent.getParcelableExtra<TVEntity>(EXTRA_TV) != null -> {
                val tvId = dataTV.id.toString()
                val overview = dataTV.overview
                val poster = dataTV.posterPath
                val backdrop = dataTV.backdropPath
                val nameTVShows = dataTV.name
                val vote = dataTV.voteAverage
                val language = dataTV.language
                val inputTVEntity =
                    TVFavEntity(
                        0,
                        tvId,
                        overview,
                        poster,
                        backdrop,
                        nameTVShows,
                        vote,
                        language
                    )
                movieViewModel.addTVFav(inputTVEntity)
                Toast.makeText(this, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
            }
            intent.getParcelableExtra<TVFavEntity>(EXTRA_FAV_TV) != null -> {
                val tvId = tvFavEntity.id.toString()
                val overview = tvFavEntity.overview
                val poster = tvFavEntity.posterPath
                val backdrop = tvFavEntity.backdropPath
                val nameTVShows = tvFavEntity.name
                val vote = tvFavEntity.voteAverage
                val language = tvFavEntity.language
                val inputTVEntity =
                    TVFavEntity(
                        0,
                        tvId,
                        overview,
                        poster,
                        backdrop,
                        nameTVShows,
                        vote,
                        language
                    )
                movieViewModel.addTVFav(inputTVEntity)
                Toast.makeText(this, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun removeFavTVShows() {
        movieViewModel = obtainViewModel(this)
        tvFavEntity.let {
            movieViewModel.deleteTVFav(it)
            movieViewModel.checkFavTV(it.tvId ?: "")
        }
        Toast.makeText(this, getString(R.string.delete_favorite), Toast.LENGTH_SHORT).show()
    }

    private fun getDataMovies(data: MoviesEntity?) {
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

    private fun getDataFavMovies(data: MoviesFavEntity?) {
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

    private fun getDataTV(data: TVEntity?) {
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

    private fun getDataFavTV(data: TVFavEntity?) {
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
}
