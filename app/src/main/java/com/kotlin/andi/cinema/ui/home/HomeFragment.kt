package com.kotlin.andi.cinema.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.ui.banner.BannerCarouselItem
import com.kotlin.andi.cinema.viewmodel.MovieViewModel
import com.kotlin.andi.cinema.viewmodel.ViewModelFactory
import com.kotlin.andi.cinema.vo.Status
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            /*getBannerData()*/
            val sectionPagerAdapter = HomePagerAdapter(requireContext(), childFragmentManager)
            view_pager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(view_pager)
        }
    }

    /*private fun getBannerData() {
        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        viewModel.getPopular().observe(viewLifecycleOwner, { popular ->
            if (popular != null) {
                popular.data?.let {
                    val bannerCarouselItem =
                        BannerCarouselItem(it, childFragmentManager)
                    it.run {
                        groupAdapter.add(bannerCarouselItem)
                    }
                }
                groupAdapter.notifyDataSetChanged()
            }
        })
        with(rv_banner) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = groupAdapter
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)
}
