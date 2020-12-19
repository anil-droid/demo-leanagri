package com.demo.leanagri.data.db

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.demo.leanagri.core.Constants
import com.demo.leanagri.data.model.detail.MovieDetail

@Dao
interface MoviesDao {



//    @Query("SELECT * FROM ${Constants.DB.Table.Movie.NAME} ORDER BY :orderBy")
//    fun getMovies(orderBy: String? = "popularity DESC"): List<MovieDetail>

    @RawQuery
    fun getMovies(query: SupportSQLiteQuery) : List<MovieDetail>

    @Query("SELECT * FROM ${Constants.DB.Table.Movie.NAME} Where id = :movieId")
    fun getMovieDetail(movieId: Int): MovieDetail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieDetail>?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(movie: MovieDetail)

    @Query("DELETE FROM ${Constants.DB.Table.Movie.NAME}")
    suspend fun clear()
    {
    }

    fun getMoviesWithSorting(orderBy: String? = "popularity DESC"): List<MovieDetail> {
        val query = "SELECT * FROM ${Constants.DB.Table.Movie.NAME} ORDER BY $orderBy"
        val simpleSQLiteQuery = SimpleSQLiteQuery(query)
        return getMovies(simpleSQLiteQuery)
    }
}