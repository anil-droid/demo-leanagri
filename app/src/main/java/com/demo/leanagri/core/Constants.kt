package com.demo.leanagri.core

class Constants {

    object API {
        const val ENDPOINT = "https://api.themoviedb.org/3/"
        const val KEY = "a213d9b91883a7b16ca2bb309690d227"
    }

    object DB {
        const val NAME = "bhara-agri-db"

        object Table {
            object Movie {
                const val NAME = "movie"
            }
        }
    }

    object Filter {

        const val POPULARITY = "popularity"
        const val VOTE = "vote_average";
        const val RELEASE_DATE = "release_date";
    }
}