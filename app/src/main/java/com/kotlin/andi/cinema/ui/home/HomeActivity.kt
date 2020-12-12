package com.kotlin.andi.cinema.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.data.PopularEntity
import com.kotlin.andi.cinema.ui.banner.BannerCarouselItem
import com.kotlin.andi.cinema.viewmodel.MovieViewModel
import com.kotlin.andi.cinema.viewmodel.ViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getBannerData()

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionPagerAdapter
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f
    }

    private fun getBannerData() {
        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        viewModel.getPopular().observe(this, { popular ->
            rv_banner.apply {
                layoutManager = LinearLayoutManager(this@HomeActivity)
                adapter = groupAdapter
            }
            val bannerCarouselItem =
                BannerCarouselItem(popular as ArrayList<PopularEntity>, supportFragmentManager)
            popular.run {
                groupAdapter.add(bannerCarouselItem)
            }
        })
    }
}
