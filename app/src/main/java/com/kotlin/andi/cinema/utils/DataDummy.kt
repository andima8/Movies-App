package com.kotlin.andi.cinema.utils

import com.kotlin.andi.cinema.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.TVFavEntity
import com.kotlin.andi.cinema.data.source.remote.response.ResultsMovies
import com.kotlin.andi.cinema.data.source.remote.response.ResultsTV

object DataDummy {

    fun generateDummyMovies(): List<ResultsMovies> {
        // Cinema
       return listOf(
                ResultsMovies(
                    602211,
                    "A rowdy, unorthodox Santa Claus is fighting to save his declining business. Meanwhile, Billy, a neglected and precocious 12 year old, hires a hit man to kill Santa after receiving a lump of coal in his stocking.",
                    "/4n8QNNdk4BOX9Dslfbz5Dy6j1HK.jpg",
                    "/ckfwfLkl0CkafTasoRw5FILhZAS.jpg",
                    "",
                    "Fatman",
                    6.1f,
                    "en"
                ),
                ResultsMovies(
                    590706,
                    "Every six years, an ancient order of jiu-jitsu fighters joins forces to battle a vicious race of alien invaders. But when a celebrated war hero goes down in defeat, the fate of the planet and mankind hangs in the balance.",
                    "/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg",
                    "/jeAQdDX9nguP6YOX6QSWKDPkbBo.jpg",
                    "",
                    "Jiu Jitsu",
                    5.7f,
                    "en"
                )
            )
    }

    // TV
    fun generateDummyTV(): List<ResultsTV> {
        return listOf(
                ResultsTV(
                    82856,
                    "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                    "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                    "/9ijMGlJKqcslswWUzTEwScm82Gs.jpg",
                    "The Mandalorian",
                    8.5f,
                    "en"
                ),
                ResultsTV(
                    71712,
                    "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?",
                    "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                    "/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
                    "The Good Doctor",
                    8.6f,
                    "en"
                )
            )
    }

    // Favorite
    fun generateDummyFavMovies(): List<MoviesFavEntity> {
        // Cinema
        return listOf(
            MoviesFavEntity(
                1,
                "602211",
                "A rowdy, unorthodox Santa Claus is fighting to save his declining business. Meanwhile, Billy, a neglected and precocious 12 year old, hires a hit man to kill Santa after receiving a lump of coal in his stocking.",
                "/4n8QNNdk4BOX9Dslfbz5Dy6j1HK.jpg",
                "/ckfwfLkl0CkafTasoRw5FILhZAS.jpg",
                "",
                "Fatman",
                6.1f,
                "en"
            ),
            MoviesFavEntity(
                2,
                "590706",
                "Every six years, an ancient order of jiu-jitsu fighters joins forces to battle a vicious race of alien invaders. But when a celebrated war hero goes down in defeat, the fate of the planet and mankind hangs in the balance.",
                "/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg",
                "/jeAQdDX9nguP6YOX6QSWKDPkbBo.jpg",
                "",
                "Jiu Jitsu",
                5.7f,
                "en"
            )
        )
    }

    fun generateDummyFavTV(): List<TVFavEntity> {
        return listOf(
            TVFavEntity(
                1,
                "82856",
                "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                "/9ijMGlJKqcslswWUzTEwScm82Gs.jpg",
                "The Mandalorian",
                8.5f,
                "en"
            ),
            TVFavEntity(
                2,
                "71712",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?",
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
                "The Good Doctor",
                8.6f,
                "en"
            )
        )
    }
    // Check Favorite Movies
    fun checkDummyFavMovies(id: String): List<MoviesFavEntity> {
        // Cinema
        return listOf(
            MoviesFavEntity(
                1,
                id,
                "A rowdy, unorthodox Santa Claus is fighting to save his declining business. Meanwhile, Billy, a neglected and precocious 12 year old, hires a hit man to kill Santa after receiving a lump of coal in his stocking.",
                "/4n8QNNdk4BOX9Dslfbz5Dy6j1HK.jpg",
                "/ckfwfLkl0CkafTasoRw5FILhZAS.jpg",
                "",
                "Fatman",
                6.1f,
                "en"
            )
        )
    }

    fun checkDummyFavTV(id: String): List<TVFavEntity> {
        return listOf(
            TVFavEntity(
                1,
                id,
                "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                "/9ijMGlJKqcslswWUzTEwScm82Gs.jpg",
                "The Mandalorian",
                8.5f,
                "en"
            )
        )
    }
}
