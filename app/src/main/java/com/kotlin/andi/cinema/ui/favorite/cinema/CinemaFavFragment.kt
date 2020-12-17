package com.kotlin.andi.cinema.ui.favorite.cinema

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.ui.home.cinema.CinemaAdapter
import com.kotlin.andi.cinema.viewmodel.MovieViewModel
import com.kotlin.andi.cinema.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_cinema_fav.*

class CinemaFavFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = CinemaFavAdapter()
            progressbar_fav_cinema.visibility = View.VISIBLE
            viewModel.readFavMovies().observe(viewLifecycleOwner, { fav ->
                if(fav != null) {
                    progressbar_fav_cinema.visibility = View.GONE
                    movieAdapter.setMovies(fav)
                    if (movieAdapter.itemCount ==0) {
                        iv_fav_notFound.visibility = View.VISIBLE
                    } else {
                       iv_fav_notFound.visibility = View.GONE
                    }
                    movieAdapter.notifyDataSetChanged()
                }
            })
            with(rv_fav_cinema_movie){
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? =  inflater.inflate(R.layout.fragment_cinema_fav, container, false)


}
