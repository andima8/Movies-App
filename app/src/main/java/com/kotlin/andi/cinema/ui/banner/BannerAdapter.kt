package com.kotlin.andi.cinema.ui.banner

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kotlin.andi.cinema.data.source.local.entity.PopularEntity
import java.util.concurrent.*

class BannerAdapter(
    fragmentManager: FragmentManager,
    private val banners: List<PopularEntity>,
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 5

    override fun getItem(position: Int): Fragment {
        return BannerFragment.newInstance(banners[position])
    }
}
