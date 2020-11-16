package com.kotlin.andi.cinema.utils

import com.kotlin.andi.cinema.R
import com.kotlin.andi.cinema.data.MovieEntity

object DataDummy {

    fun generateDummyMovies(): MutableList<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        // Cinema
        movies.add(
            MovieEntity(
                "c01",
                "Avengers: Endgame",
                R.drawable.endgame,
                R.drawable.endgame,
                "1",
                "Pasca jentikan jari Thanos (Josh Brolin), bumi kehilangan separuh populasinya. Hal ini juga turut dirasakan oleh Hawkeye (Jeremy Renner) yang secara tiba-tiba harus kehilangan keluarganya. Di sisi lain Tony Stark (Robert Downey Jr) berusaha menguatkan diri dan memupuk harapan untuk bisa kembali menyelamatkan bumi. Para Avengers yang tersisa, Black Widow (Scarlet Johansson), Hulk (Mark Rufallo), Thor (Chris Hemsworth), War Machine (Don Cheadle) memikirkan berbagai cara memungkinkan untuk menghadapi Thanos dan merebut Infinity Stone.",
                "Action, Superheroes",
                "Cinema",
                4.5F
            )
        )

        movies.add(
            MovieEntity(
                "c02",
                "The Lord of the Rings: The Fellowship of the Rings ",
                R.drawable.tlotr_banner,
                R.drawable.tlotr,
                "2",
                "Cerita dimulai ketika hobit Bilbo Baggins berangkat meninggalkan Shire dan mewariskan hartanya ke pewarisnya, Frodo. Gandalf penyihir abu-abu, seorang teman Bilbo, menasehati Frodo untuk berhati-hati dengan cincin yang menjadi salah satu warisan Bilbo. Tujuh belas tahun kemudian berlalu, dan Gandalf menyuruh Frodo untuk membawa cincin itu keluar dari Shire karena tuan kegelapan Sauron sedang mencari-cari cincin tersebut. Frodo, Samwise Gamgee dan Peregrin Took (atau Pippin) berangkat dan mereka bertemu dengan Ring-wraiths dalam perjalanan mereka. Setelah sampai di Brandyhall, mereka bertemu dengan Meriadoc Brandybuck (atau Merry) dan keempatnya mulai perjalanan mereka menuju Bree. Mereka hampir dibunuh Barrow-wights jika Tom Bombadil tidak menolong mereka.",
                "Action, War",
                "Cinema",
                4.5F
            )
        )

        movies.add(
            MovieEntity(
                "c03",
                "Spiderman: Far From Home",
                R.drawable.ffm,
                R.drawable.spiderman,
                "3",
                "Spiderman Far From Home menceritakan tentang perjalanan kehidupan Peter Parker ( Tom Holland ) setelah Avangers Endgame. Ia merasa sangat kehilangan Tony Startk alias Iroman selaku mentor dalam perjalanannya menjadi super hero. Peter tidak bisa menjadi pahlawan yang sepenuhnya karena Peter sendiri masih duduk dibangku SMA. Sampai pada akhirnya Peter tidak ingin melanjutkan menjadi Spider-Man saat sedang berlibur bersama teman-temanya.",
                "Action, Superheroes",
                "Cinema",
                4.0F
            )
        )
        movies.add(
            MovieEntity(
                "c04",
                "Venom",
                R.drawable.venom,
                R.drawable.venom,
                "4",
                "Venom menceritakan kisah Eddie Brock (Tom Hardy) seorang jurnalis yang mempertanyakan kasus yang dihindari oleh pemerintah. ... Hal itu membuatnya melakukan investigasi pada perusahaan Life Foundation yang dijalankan oleh Carlton Drake (Riz Ahmed).",
                "Action, Superheroes",
                "Cinema",
                4.0F
            )
        )

        movies.add(
            MovieEntity(
                "c05",
                "Star Wars Episode III: Revenge of the Sith",
                R.drawable.starwars,
                R.drawable.starwars,
                "5",
                "Pada pertempuran luar angkasa di dekat Coruscant antara Republik dan Separtist, Anakin Skywalker dan Obi-Wan Kenobi berencana menyelamatkan Supreme Chancellor Palpatine yang telah diculik oleh komandan Separatist General Grievous. Setelah melawan beberapa droid dan memasuki kapal Grievous, sang Jedi menghadapi Count Dooku, yang melawan mereka dalam duel lightsaber. Setelah Dooku memingsankan Obi-Wan, Anakin melawan dan mebunuh Dooku karena perintah Palpatine. Setelah pertempuran kecil Grievous, yang kemudian melarikan diri, sang Jedi melakukan pendaratan darurat di Coruscant. Di sana, Anakin bertemu dengan istrinya, Padmé Amidala, yang sedang hamil. Pada awalnya dia senang, tetapi kemudian Anakin mulai berfirasat Padmé mati ketika melahirkan.",
                "Action, Sci-fi",
                "Cinema",
                4.0F
            )
        )

        //TV Shows
        movies.add(
            MovieEntity(
                "tv01",
                "The Walking Dead",
                R.drawable.twd_banner,
                R.drawable.twd,
                "6",
                "Deputi sherif Rick Grimes terbangun dari koma, menemukan dunia dikuasai oleh zombi. Setelah berteman dengan Morgan Jones, yang kemudian berpisah dengannya, Rick pergi ke Atlanta untuk menemukan istrinya Lori, anaknya, Carl, dan rekan polisinya, Shane Walsh, bertemu dengan penyintas lainnya. Kelompok tersebut pergi ke Pusat Pengendalian dan Pencegahan Penyakit (CDC), tetapi mereka mengetahui dari satu-satunya anggota CDC yang tersisa bahwa tidak ada obat untuk epidemi ini.",
                "Action, Horror",
                "TV Shows",
                4.0F
            )
        )

        movies.add(
            MovieEntity(
                "tv02",
                "Chernobyl",
                R.drawable.chernobyl_banner,
                R.drawable.chernobyl,
                "7",
                "Pada bulan April 1986, ledakan di pembangkit listrik tenaga nuklir Chernobyl di Uni Republik Sosialis Soviet menjadi salah satu bencana buatan manusia yang terburuk di dunia.",
                "Drama, History",
                "TV Shows",
                4.0F
            )
        )

        movies.add(
            MovieEntity(
                "tv03",
                "One Piece",
                R.drawable.onepiece,
                R.drawable.onepiece,
                "8",
                "One Piece menceritakan tentang petualangan seorang anak bernama Monkey D. Luffy yang bercita-cita menjadi raja bajak laut dan menemukan \"One Piece\" setelah terinspirasi oleh Shanks.",
                "Action, Animation",
                "TV Shows",
                4.0F
            )
        )

        movies.add(
            MovieEntity(
                "tv04",
                "One Punch Man",
                R.drawable.onepunchman,
                R.drawable.onepunchman,
                "9",
                "Di planet super benua yang tidak disebutkan namanya, monster aneh dan supervillains secara misterius muncul dan menyebabkan bencana. Untuk memerangi mereka, pahlawan dunia telah bangkit untuk melawan mereka. Saitama adalah salah satu pahlawan yang mampu mengatasi mereka.",
                "Animation, Superheroes",
                "TV Shows",
                4.0F
            )
        )

        movies.add(
            MovieEntity(
                "tv05",
                "Money Heist",
                R.drawable.banner_mh,
                R.drawable.moneyheist,
                "10",
                "Sekelompok perampok yang tidak biasa mencoba melakukan perampokan paling sempurna dalam sejarah Spanyol - mencuri 2,4 miliar euro dari Royal Mint Spanyol.",
                "Drama, Crime",
                "TV Shows",
                4.0F
            )
        )
        return movies
    }
}
