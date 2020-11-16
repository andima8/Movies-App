package com.kotlin.andi.cinema.ui.home.all
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.ui.home.HomeActivity
import com.kotlin.andi.cinema.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class AllMoviesFragmentTest {
    private val dataMovie = DataDummy.generateDummyMovies()

    @get:Rule
    var mActivityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun checkBanner() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_banner)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_banner))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataMovie.size)
            )
        onView(withId(R.id.rv_banner))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
    }

    @Test
    fun loadAllMovies() {
        onView(withId(R.id.rv_all_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_all_movie))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataMovie.size)
            )
        onView(withId(R.id.rv_all_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
        onView(withId(R.id.collapsing_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_img)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_desc)).check(matches(isDisplayed()))
    }

    @Test
    fun loadCinemaMovies() {
        onView(ViewMatchers.withText("Cinema")).perform(click())
        onView(withId(R.id.rv_cinema_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_cinema_movie))
            .perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataMovie.size)
        )
        onView(withId(R.id.rv_cinema_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
        onView(withId(R.id.collapsing_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_img)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_desc)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTVShowsMovies() {
        onView(ViewMatchers.withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_movie))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataMovie.size)
            )
        onView(withId(R.id.rv_tv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
        onView(withId(R.id.collapsing_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_img)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_desc)).check(matches(isDisplayed()))
    }
}
