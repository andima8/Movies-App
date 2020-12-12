package com.kotlin.andi.cinema.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.ui.home.cinema.CinemaFragment
import com.kotlin.andi.cinema.ui.home.tvshows.TvShowsFragment

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.cinema, R.string.tv_shows)
    }

    override fun getCount(): Int = TAB_TITLES.size

    override fun getItem(position: Int): Fragment =
        when(position) {
            0 -> CinemaFragment()
            else -> TvShowsFragment()
        }
    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(TAB_TITLES[position])
}
