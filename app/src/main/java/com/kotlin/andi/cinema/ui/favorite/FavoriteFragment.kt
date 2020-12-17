package com.kotlin.andi.cinema.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.andi.cinema.R
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val favoritePagerAdapter = FavoritePagerAdapter(requireContext(), childFragmentManager)
        view_pager_fav.adapter = favoritePagerAdapter
        tabs_fav.setupWithViewPager(view_pager_fav)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_favorite, container, false)


}
