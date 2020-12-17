package com.kotlin.andi.cinema.ui.home.cinema

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.viewmodel.MovieViewModel
import com.kotlin.andi.cinema.viewmodel.ViewModelFactory
import com.kotlin.andi.cinema.vo.Status
import kotlinx.android.synthetic.main.fragment_cinema.*

class CinemaFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = CinemaAdapter()
            progressbar_cinema.visibility = View.VISIBLE
            viewModel.getAllMovies().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> progressbar_cinema.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            progressbar_cinema.visibility = View.GONE
                            movies.data?.let { movieAdapter.setMovies(it) }
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR ->{
                            progressbar_cinema.visibility = View.GONE
                            Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
            with(rv_cinema_movie) {
                layoutManager = GridLayoutManager(activity, 2)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_cinema, container, false)
}
