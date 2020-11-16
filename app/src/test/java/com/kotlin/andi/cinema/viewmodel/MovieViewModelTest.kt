package com.kotlin.andi.cinema.viewmodel

import com.kotlin.andi.cinema.data.MovieEntity
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

@RunWith(JUnitPlatform::class)
class MovieViewModelTest : Spek({

    lateinit var viewModel: MovieViewModel
    lateinit var movieEntities: List<MovieEntity>
    var totalMovies = 0

    // getMovies
    Feature("Showing All Movies") {
        Scenario("Get Data from object dummy via ViewModel") {
            Given("Initialize ViewModel and Expected total data") {
                viewModel = MovieViewModel()
                totalMovies = 10
            }
            When("Get data from ViewModel") {
                movieEntities = viewModel.getMovies()
            }
            Then("It should show all movies") {
                assertEquals(totalMovies, movieEntities.size)
            }
        }
    }

    // getCinema
    Feature("Showing Only Cinema Movies") {
        Scenario("Get only cinema movies") {
            Given("Initialize ViewModel and Total data") {
                viewModel = MovieViewModel()
                totalMovies = 5
            }
            When("Get data from ViewModel and filter Cinema Only") {
                movieEntities = viewModel.getMovies().filter { it.typeMovie == "Cinema" }
            }
            Then("It should show Cinema movies") {
                assertEquals(totalMovies, movieEntities.size)
            }
        }
    }

    // getTVShows
    Feature("Showing Only TV Shows Movies") {
        Scenario("Get only TV Shows movies") {
            Given("Initialize ViewModel and Total data") {
                viewModel = MovieViewModel()
                totalMovies = 5
            }
            When("Get data from ViewModel and filter Cinema Only") {
                movieEntities = viewModel.getMovies().filter { it.typeMovie == "TV Shows" }
            }
            Then("It should show TV Shows movies") {
                assertEquals(totalMovies, movieEntities.size)
            }
        }
    }
})
