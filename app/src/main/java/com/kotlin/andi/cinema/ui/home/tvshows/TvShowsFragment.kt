package com.kotlin.andi.cinema.ui.home.tvshows

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.viewmodel.MovieViewModel
import com.kotlin.andi.cinema.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv_shows.*

class TvShowsFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val tvAdapter = TVAdapter()
            progressbar_tv.visibility = View.VISIBLE
            viewModel.getAllTVShows().observe(viewLifecycleOwner, { tv ->
                progressbar_tv.visibility = View.GONE
                tvAdapter.setMovies(tv)
                Log.d("TV FRAG", "$tv")
                tvAdapter.notifyDataSetChanged()
                with(rv_tv_movie) {
                    layoutManager = GridLayoutManager(activity, 2)
                    setHasFixedSize(true)
                    adapter = tvAdapter
                }
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tv_shows, container, false)
}
