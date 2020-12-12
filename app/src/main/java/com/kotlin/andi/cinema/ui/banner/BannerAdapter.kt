package com.kotlin.andi.cinema.ui.banner

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kotlin.andi.cinema.data.PopularEntity

class BannerAdapter(
    fragmentManager: FragmentManager,
    private val banners: ArrayList<PopularEntity>
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 5

    override fun getItem(position: Int): Fragment {
        val movie = banners[position]
        return BannerFragment.newInstance(movie)
    }
}
