package com.kotlin.andi.cinema.ui.banner

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.FragmentManager
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.data.PopularEntity
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.items_banner.view.*
import java.util.*

class BannerCarouselItem(
    private val banners: ArrayList<PopularEntity>,
    private val supportFragmentManager: FragmentManager
) : Item<GroupieViewHolder>() {

    companion object {
        const val TOTAL_PAGE = 5
        const val TIMER = 2000
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        var currentPage = 0
        val viewPagerAdapter = BannerAdapter(supportFragmentManager, banners)

        viewHolder.itemView.apply {
            view_pager_banner.adapter = viewPagerAdapter
            banner_indicator.setViewPager(viewHolder.itemView.view_pager_banner)
        }
        // Membuat auto slide pada banner
        val update = Runnable {
            if (currentPage == TOTAL_PAGE) {
                currentPage = 0
            }
            viewHolder.itemView.view_pager_banner.setCurrentItem(currentPage++, true)
        }
        // Membuat Thread untuk Timer
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            // Menjadwalkan penggantian gambar banner
            override fun run() {
                Handler(Looper.getMainLooper()).post(update)
            }
        }, TIMER.toLong(), TIMER.toLong())
    }

    override fun getLayout(): Int = R.layout.items_banner
}
