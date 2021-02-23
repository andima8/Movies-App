package com.kotlin.andi.cinema.home.cinema

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.core.utils.invisible
import com.kotlin.andi.cinema.core.utils.visible
import com.kotlin.andi.cinema.core.ui.viewmodel.HomeViewModel
import com.kotlin.andi.cinema.core.vo.Status
import com.kotlin.andi.cinema.core.ui.adapter.CinemaAdapter
import kotlinx.android.synthetic.main.fragment_cinema.*
import org.koin.android.viewmodel.ext.android.viewModel

class CinemaFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val movieAdapter = CinemaAdapter()
            progressbar_cinema.visible()
            homeViewModel.getAllMovies.observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> progressbar_cinema.visible()
                        Status.SUCCESS -> {
                            progressbar_cinema.invisible()
                            movieAdapter.submitList(movies.data)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            progressbar_cinema.invisible()
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
