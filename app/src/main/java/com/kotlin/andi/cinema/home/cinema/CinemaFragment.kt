package com.kotlin.andi.cinema.home.cinema

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.databinding.FragmentCinemaBinding
import com.kotlin.andi.cinema.detail.DetailActivity
import com.kotlin.andi.cinema.detail.DetailActivity.Companion.EXTRA_MOVIE
import com.kotlin.andi.cinema.home.HomeViewModel
import com.kotlin.andi.core.ui.adapter.CinemaAdapter
import com.kotlin.andi.core.utils.invisible
import com.kotlin.andi.core.utils.visible
import com.kotlin.andi.core.vo.Status
import org.koin.android.viewmodel.ext.android.viewModel

class CinemaFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentCinemaBinding? = null
    private val binding get() = _binding!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            val movieAdapter = CinemaAdapter {
                val detailIntent = Intent(activity, DetailActivity::class.java)
                detailIntent.putExtra(EXTRA_MOVIE, it)
                startActivity(detailIntent)
            }
            binding.progressbarCinema.visible()
            homeViewModel.getAllMovies.observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> binding.progressbarCinema.visible()
                        Status.SUCCESS -> {
                            binding.progressbarCinema.invisible()
                            movieAdapter.submitList(movies.data)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            binding.progressbarCinema.invisible()
                            Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                with(binding.rvCinemaMovie) {
                    layoutManager = GridLayoutManager(activity, 2)
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }
            })

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCinemaBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
