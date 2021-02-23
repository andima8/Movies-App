package com.kotlin.andi.cinema.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.favorite.FavoriteFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
        }
        bottomNavigation()
    }

    private fun loadHomeFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, HomeFragment(), HomeFragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

    private fun loadFavoriteFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container,
                FavoriteFragment(),
                FavoriteFragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

    private fun bottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    loadHomeFragment()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.favorite -> {
                    loadFavoriteFragment()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.home
    }
}
