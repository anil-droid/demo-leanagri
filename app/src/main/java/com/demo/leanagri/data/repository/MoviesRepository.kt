package com.demo.leanagri.data.repository

import com.demo.leanagri.core.Constants
import com.demo.leanagri.data.db.MoviesDao
import com.demo.leanagri.data.model.detail.MovieDetail
import com.demo.leanagri.data.source.*
import com.demo.leanagri.data.Result
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(private val dao: MoviesDao,
                                           private val remoteSource: MoviesRemoteDataSource
) {


    fun movieDetail(movieId: Int, queryMap: HashMap<String, Any>) : Flow<Result<MovieDetail?>> = getMovieDetail(
        databaseQuery = { dao.getMovieDetail(movieId) },
        networkCall = { remoteSource.getMovieDetail(movieId, queryMap) },
        saveCallResult = { dao.update(it!!) })

    suspend fun loadMovies(queryMap: HashMap<String, Any>): Flow<Result<List<MovieDetail>>> {

        val orderBy = getColumnOrderBy(queryMap);
        return getMoviesList(
            databaseQuery = { dao.getMoviesWithSorting(orderBy) },
            networkCall = { remoteSource.getMovies(queryMap) },
            saveCallResult = { dao.insertAll(it.results) },
        )
    }


    private fun getColumnOrderBy(queryMap: HashMap<String, Any>): String? {

       return queryMap["sort_by"]?.toString()?.split(".")?.takeIf { it.size > 1 }?.run {
           when(this[0])
           {
               Constants.Filter.RELEASE_DATE -> "releaseDate"
               Constants.Filter.VOTE -> "voteAverage"
               else ->{Constants.Filter.POPULARITY}

           }.plus(" ").plus(this[1].toUpperCase(Locale.ROOT))
        }
    }


}