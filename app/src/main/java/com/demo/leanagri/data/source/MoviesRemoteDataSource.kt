package com.demo.leanagri.data.source

import com.demo.leanagri.data.api.MoviesAPIService
import javax.inject.Inject

open class MoviesRemoteDataSource @Inject constructor(
    private val moviesAPIService : MoviesAPIService
): BaseDataSource() {

    suspend fun getMovies(queryMap: HashMap<String, Any>) = getResult { moviesAPIService.getMovies(queryMap) }

    suspend fun getMovieDetail(movieId: Int, queryMap: HashMap<String, Any>) = getResult { moviesAPIService.getMovieDetail(movieId, queryMap) }

}