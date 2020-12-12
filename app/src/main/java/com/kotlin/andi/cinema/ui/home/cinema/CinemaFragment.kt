package com.kotlin.andi.cinema.ui.home.cinema

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
import kotlinx.android.synthetic.main.fragment_cinema.*

class CinemaFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = CinemaAdapter()
            progressbar_cinema.visibility = View.VISIBLE
            viewModel.getAllMovies().observe(viewLifecycleOwner, { data ->
                progressbar_cinema.visibility = View.GONE
                movieAdapter.setMovies(data)
                Log.d("CINEMA FRAGMENT", "$data")
                movieAdapter.notifyDataSetChanged()
                with(rv_cinema_movie) {
                    layoutManager = GridLayoutManager(activity, 2)
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }
            })
            /*movieViewModel.isLoading.observe(requireActivity(), {
                progressBar.visibility = if (it) View.VISIBLE else View. GONE
            })*/
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_cinema, container, false)
}
