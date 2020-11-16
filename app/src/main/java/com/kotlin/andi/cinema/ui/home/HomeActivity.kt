package com.kotlin.andi.cinema.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.ui.banner.BannerCarouselItem
import com.kotlin.andi.cinema.data.MovieEntity
import com.kotlin.andi.cinema.viewmodel.MovieViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var movies: MutableList<MovieEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getBannerData()
        setBannerAdapter()

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionPagerAdapter
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f
    }

    private fun getBannerData() {
        val viewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        )[MovieViewModel::class.java]
        movies = viewModel.getMovies()
    }

    private fun setBannerAdapter() {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        rv_banner.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = groupAdapter
        }

        val bannerCarouselItem = BannerCarouselItem(movies, supportFragmentManager)
        movies.run {
            groupAdapter.add(bannerCarouselItem)
            shuffle()
        }
    }
}
