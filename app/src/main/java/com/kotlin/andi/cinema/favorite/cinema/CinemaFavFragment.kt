package com.kotlin.andi.cinema.favorite.cinema

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.detail.DetailActivity
import com.kotlin.andi.cinema.favorite.FavoriteViewModel
import com.kotlin.andi.core.ui.adapter.CinemaFavAdapter
import com.kotlin.andi.core.utils.invisible
import com.kotlin.andi.core.utils.visible
import kotlinx.android.synthetic.main.fragment_cinema_fav.*
import org.koin.android.viewmodel.ext.android.viewModel

class CinemaFavFragment : Fragment() {

    private lateinit var movieAdapter: CinemaFavAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            movieAdapter = CinemaFavAdapter{
                val detailIntent = Intent(activity, DetailActivity::class.java)
                detailIntent.putExtra(DetailActivity.EXTRA_FAV_MOVIE, it)
                startActivity(detailIntent)
            }
            progressbar_fav_cinema.visible()
            rv_fav_cinema_movie.adapter = movieAdapter
            rv_fav_cinema_movie.layoutManager = LinearLayoutManager(requireContext())
            favoriteViewModel.readFavMovies.observe(viewLifecycleOwner, { fav ->
                if (fav != null) {
                    progressbar_fav_cinema.invisible()
                    movieAdapter.submitList(fav)
                    if (movieAdapter.itemCount == 0) {
                        tv_fav_notFound.visible()
                        iv_fav_notFound.visible()
                    } else {
                        tv_fav_notFound.invisible()
                        iv_fav_notFound.invisible()
                    }
                }
            })
            itemTouchHelper.attachToRecyclerView(rv_fav_cinema_movie)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_cinema_fav, container, false)

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movies = movieAdapter.getSwipedData(swipedPosition)
                movies?.let {
                    favoriteViewModel.deleteMoviesFav(it)
                }
                val confirmation = Snackbar.make(
                    view as View,
                    R.string.message_undo,
                    Snackbar.LENGTH_LONG)
                confirmation.setAction(R.string.message_ok) { _ ->
                    movies?.let {
                        favoriteViewModel.addMoviesFav(it)
                    }
                }
                confirmation.show()
            }
        }
    })
}
