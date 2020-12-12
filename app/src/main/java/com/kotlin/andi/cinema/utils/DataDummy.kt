package com.kotlin.andi.cinema.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.andi.cinema.data.source.remote.response.ResultsMovies
import com.kotlin.andi.cinema.data.source.remote.response.ResultsPopular
import com.kotlin.andi.cinema.data.source.remote.response.ResultsTV

object DataDummy {

    fun generateDummyMovies(): MutableLiveData<List<ResultsMovies>> {
        // Cinema
       return MutableLiveData(
            listOf(
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
        )
    }

    // TV
    fun generateDummyTV(): LiveData<List<ResultsTV>> {
        return MutableLiveData(
            listOf(
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
        )
    }

    // Popular
    fun generateDummyPopular(): LiveData<List<ResultsPopular>> {
        return MutableLiveData(
            listOf(
                ResultsPopular(
                    577922,
                    "Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                    "/k68nPLbIST6NP96JmTxmZijEvCA.jpg",
                    "/wzJRB4MKi3yK138bJyuL9nx47y6.jpg",
                    "",
                    "Tenet",
                    7.4f
                ),
                ResultsPopular(
                    82856,
                    "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                    "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                    "/9ijMGlJKqcslswWUzTEwScm82Gs.jpg",
                    "The Mandalorian",
                    "",
                    8.5f
                )
            )
        )
    }
}
