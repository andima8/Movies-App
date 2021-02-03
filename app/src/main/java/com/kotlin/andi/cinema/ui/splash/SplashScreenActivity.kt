package com.kotlin.andi.cinema.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.ui.home.HomeActivity
import com.kotlin.andi.cinema.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    companion object {
        const val A = 3
        const val B = 1000
    }

    private val splashScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        // Start Animation
        EspressoIdlingResource.increment()
        sp_animation.playAnimation()
        splashScope.launch(Dispatchers.IO) {
            for (i in 0 until A) {
                Thread.sleep(B.toLong())
            }
            startActivity()
            EspressoIdlingResource.decrement()
        }
    }

    private fun startActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onPause() {
        splashScope.cancel()
        super.onPause()
    }
}
