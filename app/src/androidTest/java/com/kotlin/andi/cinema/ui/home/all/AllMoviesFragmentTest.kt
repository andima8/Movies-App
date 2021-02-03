package com.kotlin.andi.cinema.ui.home.all
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.ui.splash.SplashScreenActivity
import com.kotlin.andi.cinema.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AllMoviesFragmentTest {

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @get:Rule
    var mActivityRule = ActivityScenarioRule(SplashScreenActivity::class.java)

    @Test
    fun loadCinemaMovies() {
        // Home
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.home)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withText("Cinema")).perform(click())
        onView(withId(R.id.rv_cinema_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_cinema_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
        onView(withId(R.id.collapsing_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_img)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_language)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()))
        // Favorite
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.favorite)).perform(click())
        onView(withId(R.id.tabs_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager_fav)).check(matches(isDisplayed()))
        onView(withText("Cinema")).perform(click())
        onView(withId(R.id.rv_fav_cinema_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_cinema_movie))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
        onView(withId(R.id.collapsing_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_img)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_language)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadTVShowsMovies() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.home)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
        onView(withId(R.id.collapsing_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_img)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_language)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()))
        // Favorite
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.favorite)).perform(click())
        onView(withId(R.id.tabs_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager_fav)).check(matches(isDisplayed()))
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_fav_tv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_tv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
        onView(withId(R.id.collapsing_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_img)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_language)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }
}
