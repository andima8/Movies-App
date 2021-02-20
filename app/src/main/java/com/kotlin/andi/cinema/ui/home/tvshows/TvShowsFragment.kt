package com.kotlin.andi.cinema.ui.home.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.utils.invisible
import com.kotlin.andi.cinema.utils.visible
import com.kotlin.andi.cinema.viewmodel.HomeViewModel
import com.kotlin.andi.cinema.viewmodel.ViewModelFactory
import com.kotlin.andi.cinema.vo.Status
import kotlinx.android.synthetic.main.fragment_tv_shows.*

class TvShowsFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
            val tvAdapter = TVAdapter()
            progressbar_tv.visible()
            viewModel.getAllTVShows().observe(viewLifecycleOwner, { tv ->
               if (tv != null) {
                   when (tv.status) {
                       Status.LOADING -> progressbar_tv.visible()
                       Status.SUCCESS -> {
                           progressbar_tv.invisible()
                           tvAdapter.submitList(tv.data)
                       }
                       Status.ERROR -> {
                           progressbar_tv.invisible()
                           Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
                       }
                   }
               }
            })
            with(rv_tv_movie) {
                layoutManager = GridLayoutManager(activity, 2)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tv_shows, container, false)
}
