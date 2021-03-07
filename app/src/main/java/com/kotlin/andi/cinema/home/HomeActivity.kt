package com.kotlin.andi.cinema.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.databinding.ActivityHomeBinding
import java.io.IOException

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigationToFragment(HomeFragment())
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
        }
        bottomNavigation()
    }

    private fun loadFavoriteFragment() {
        val fragment = instantiateFragment()
        if (fragment != null) { navigationToFragment(fragment) }
    }

    private fun navigationToFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container,
                fragment)
            .commit()
    }

    private fun instantiateFragment(): Fragment? {
        return try {
            Class.forName("com.kotlin.andi.favorite.FavoriteFragment").newInstance() as Fragment
        } catch (e: IOException) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
            null
        }
    }

    private fun bottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    navigationToFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.favorite -> {
                    loadFavoriteFragment()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            true
        }
        binding.bottomNavigation.selectedItemId = R.id.home
    }
}
