package com.kotlin.andi.cinema.ui.banner

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kotlin.andi.cinema.BuildConfig
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.data.PopularEntity
import com.kotlin.andi.cinema.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_banner.*

class BannerFragment : Fragment() {

    companion object {
        private const val MOVIE = "movie"
        fun newInstance(popular: PopularEntity): BannerFragment {
            val newFragment = BannerFragment()
            val movieData = Bundle()
            movieData.putParcelable(MOVIE, popular)
            newFragment.arguments = movieData
            return newFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movies = arguments?.getParcelable<PopularEntity>(MOVIE)
        Glide.with(this)
            .load(BuildConfig.BASE_IMG_URL + movies?.backdropPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(imgBanner)
        imgBanner.setOnClickListener {
            val detailIntent = Intent(activity, DetailActivity::class.java)
            detailIntent.putExtra(DetailActivity.EXTRA_POPULAR, movies)
            startActivity(detailIntent)
        }
        val title: String
        title = when {
            movies?.title.isNullOrEmpty() -> {
                movies?.name.toString()
            }
            else -> {
                movies?.title.toString()
            }
        }
        movie_name.text = title
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_banner, container, false)
}
