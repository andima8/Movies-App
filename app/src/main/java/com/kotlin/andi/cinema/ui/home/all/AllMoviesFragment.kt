package com.kotlin.andi.cinema.ui.home.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.ui.home.HomeAdapter
import com.kotlin.andi.cinema.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_all_movies.*

class AllMoviesFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            // Get data from viewModel
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieViewModel::class.java]
            val movies = viewModel.getMovies()
            // Set Data to adapter
            val movieAdapter = HomeAdapter()
            movieAdapter.setMovies(movies)
            // Set Adapter position
            rv_all_movie.layoutManager = GridLayoutManager(activity, 2)
            rv_all_movie.setHasFixedSize(true)
            rv_all_movie.adapter = movieAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_all_movies, container, false)
}
