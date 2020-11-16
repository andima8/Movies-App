package com.kotlin.andi.cinema.ui.banner

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.data.MovieEntity
import com.kotlin.andi.cinema.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_banner.*

class BannerFragment : Fragment() {

    companion object {
        private const val MOVIE = "movie"
        fun newInstance(movieEntity: MovieEntity): BannerFragment {
            val newFragment = BannerFragment()
            val movieData = Bundle()
            movieData.putParcelable(MOVIE, movieEntity)
            newFragment.arguments = movieData
            return newFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movies = arguments?.getParcelable<MovieEntity>(MOVIE)
        Glide.with(this)
            .load(movies?.bannerMovie)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(imgBanner)
        imgBanner.setOnClickListener {
            val detailIntent = Intent(activity, DetailActivity::class.java)
            detailIntent.putExtra(DetailActivity.EXTRA_MOVIE, movies)
            startActivity(detailIntent)
        }
        movie_name.text = movies?.nameMovie
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_banner, container, false)
}
