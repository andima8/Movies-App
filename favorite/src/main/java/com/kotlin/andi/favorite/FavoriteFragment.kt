package com.kotlin.andi.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.andi.favorite.R
import com.kotlin.andi.favorite.di.favoriteModule
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadKoinModules(favoriteModule)
        val favoritePagerAdapter = FavoritePagerAdapter(requireContext(), childFragmentManager)
        view_pager_fav.adapter = favoritePagerAdapter
        tabs_fav.setupWithViewPager(view_pager_fav)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorite, container, false)
}
