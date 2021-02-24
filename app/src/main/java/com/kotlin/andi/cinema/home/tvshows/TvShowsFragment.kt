package com.kotlin.andi.cinema.home.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.databinding.FragmentTvShowsBinding
import com.kotlin.andi.cinema.detail.DetailActivity
import com.kotlin.andi.cinema.home.HomeViewModel
import com.kotlin.andi.core.ui.adapter.TVAdapter
import com.kotlin.andi.core.utils.invisible
import com.kotlin.andi.core.utils.visible
import com.kotlin.andi.core.vo.Status
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowsFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val tvAdapter = TVAdapter {
                val detailIntent = Intent(activity, DetailActivity::class.java)
                detailIntent.putExtra(DetailActivity.EXTRA_TV, it)
                startActivity(detailIntent)
            }
            binding.progressbarTv.visible()
            homeViewModel.getAllTVShows.observe(viewLifecycleOwner, { tv ->
               if (tv != null) {
                   when (tv.status) {
                       Status.LOADING -> binding.progressbarTv.visible()
                       Status.SUCCESS -> {
                           binding.progressbarTv.invisible()
                           tvAdapter.submitList(tv.data)
                       }
                       Status.ERROR -> {
                           binding.progressbarTv.invisible()
                           Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
                       }
                   }
               }
            })
            with(binding.rvTvMovie) {
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
    ): View? {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
