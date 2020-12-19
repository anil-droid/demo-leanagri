package com.demo.leanagri.data.api

import com.demo.leanagri.data.model.detail.MovieDetail
import com.demo.leanagri.data.model.list.MoviesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MoviesAPIService {

    @GET("discover/movie/")
    suspend fun getMovies(
        @QueryMap query: HashMap<String, Any>): Response<MoviesListResponse>

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @QueryMap query: HashMap<String, Any>): Response<MovieDetail>
}