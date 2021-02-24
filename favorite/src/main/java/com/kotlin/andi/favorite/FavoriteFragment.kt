package com.kotlin.andi.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kotlin.andi.favorite.databinding.FragmentFavoriteBinding
import com.kotlin.andi.favorite.di.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadKoinModules(favoriteModule)
        val favoritePagerAdapter = FavoritePagerAdapter(requireContext(), childFragmentManager)
        binding.viewPagerFav.adapter = favoritePagerAdapter
        binding.tabsFav.setupWithViewPager(binding.viewPagerFav)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
