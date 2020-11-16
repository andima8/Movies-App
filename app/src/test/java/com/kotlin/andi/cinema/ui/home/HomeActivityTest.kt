package com.kotlin.andi.cinema.ui.home
import com.kotlin.andi.cinema.data.MovieEntity
import com.kotlin.andi.cinema.viewmodel.MovieViewModel
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

@RunWith(JUnitPlatform::class)
class HomeActivityTest : Spek ({

    lateinit var viewModel: MovieViewModel
    lateinit var movies: MutableList<MovieEntity>
    lateinit var movieEntities: List<MovieEntity>
    var totalMovies = 0

    // Get Banner Data
    Feature("Show Banner Movie") {
        Scenario("Get data from object dummy") {

            Given("Initialize ViewModel") {
                viewModel = MovieViewModel()
                totalMovies = 10
            }
            When("Get Data from ViewModel") {
                movies = viewModel.getMovies()
            }
            Then("Shuffle and Show Data to Banner") {
                movies.run {
                    shuffle()
                }
                assertEquals(totalMovies, movies.size)
            }
        }
    }
    // Get All Movies
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
    // Get Cinema Movies
    Feature("Showing Cinema Movies") {
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
    // Get TV Shows Movies
    Feature("Showing TV Shows Movies") {
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
