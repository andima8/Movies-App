package com.kotlin.andi.favorite.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kotlin.andi.cinema.detail.DetailActivity
import com.kotlin.andi.favorite.FavoriteViewModel
import com.kotlin.andi.core.ui.adapter.TvShowsFavAdapter
import com.kotlin.andi.core.utils.invisible
import com.kotlin.andi.core.utils.visible
import com.kotlin.andi.favorite.R
import com.kotlin.andi.favorite.databinding.FragmentTvShowsFavBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowsFavFragment : Fragment() {

    private lateinit var tvShowsFavAdapter: TvShowsFavAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentTvShowsFavBinding? = null
    private val binding get() = _binding!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            tvShowsFavAdapter = TvShowsFavAdapter {
                val detailIntent = Intent(activity, DetailActivity::class.java)
                detailIntent.putExtra(DetailActivity.EXTRA_FAV_TV, it)
                startActivity(detailIntent)
            }
            with(binding) {
                progressbarFavTv.visible()
                favoriteViewModel.readFavTV.observe(viewLifecycleOwner, { fav ->
                    if (fav != null) {
                        progressbarFavTv.invisible()
                        tvShowsFavAdapter.submitList(fav)
                        if (tvShowsFavAdapter.itemCount == 0) {
                            tvFavTvNotFound.visible()
                            ivFavTvNotFound.visible()
                        } else {
                            tvFavTvNotFound.invisible()
                            ivFavTvNotFound.invisible()
                        }
                    }
                    tvShowsFavAdapter.notifyDataSetChanged()
                })
                itemTouchHelper.attachToRecyclerView(rvFavTvMovie)
                with(rvFavTvMovie) {
                    layoutManager = LinearLayoutManager(activity)
                    setHasFixedSize(true)
                    adapter = tvShowsFavAdapter
                }
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        _binding = FragmentTvShowsFavBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        itemTouchHelper.attachToRecyclerView(null)
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder:
            RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val tv = tvShowsFavAdapter.getSwipedData(swipedPosition)
                tv?.let { favoriteViewModel.deleteTVFav(it) }
                val confirmation = Snackbar.make(
                    view as View,
                    R.string.message_undo,
                    Snackbar.LENGTH_LONG)
                confirmation.setAction(R.string.message_ok) { _ ->
                    tv?.let {
                        favoriteViewModel.addTVFav(it)
                        binding.ivFavTvNotFound.visibility = View.GONE
                    }
                }
                confirmation.show()
            }
        }
    })
}
