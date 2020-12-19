package com.demo.leanagri.ui.movie.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.demo.leanagri.data.model.detail.MovieDetail
import com.demo.leanagri.data.repository.MoviesRepository
import com.demo.leanagri.core.Constants
import com.demo.leanagri.data.Result
import com.demo.leanagri.utils.desc
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.collections.HashMap
import kotlin.collections.List
import kotlin.collections.set

class MoviesViewModel @ViewModelInject constructor(private val repository: MoviesRepository) : ViewModel() {

    var movies = MutableLiveData<List<MovieDetail>>()
    var movieDetail = MutableLiveData<MovieDetail>()
    var error = MutableLiveData<String>()

    var isLastPage = false
    var isLoading = true

    private var queryMap = HashMap<String, Any>()

    init {
        queryMap["api_key"] = Constants.API.KEY
        queryMap["language"] = "en-US"
        queryMap["sort_by"] = Constants.Filter.POPULARITY.desc()
        queryMap["include_adult"] = false
        queryMap["include_video"] = false
        queryMap["page"] = 0
    }

    fun loadMovies() {
        isLoading = true
        queryMap["page"] = queryMap["page"].toString().toInt()+1

        viewModelScope.launch {
            repository.loadMovies(queryMap).collect{

                when(it)
                {

                    Result.Loading ->
                    {

                    }
                    is Result.Success -> {
                        movies.value = it.data
                    }
                    is Result.Error ->{
                        isLastPage = true
                        error.value = it.error

                    }
                }
            }

        }

    }

    fun loadMovieDetail(movieId: Int) {
        val queryMap = HashMap<String, Any>()
        queryMap["api_key"] = Constants.API.KEY
        queryMap["language"] = "en-US"

        viewModelScope.launch {
            repository.movieDetail(movieId,queryMap).collect {
                when (it)
                {
                   is Result.Success ->{
                        movieDetail.value = it.data
                    }
                }
            }
        }
    }

    fun applyFilter(filter: String) {

        resetQuery()
        queryMap["sort_by"] = filter
        loadMovies()


    }

    private fun resetQuery() {
        queryMap["page"] = 0
        queryMap["sort_by"] = Constants.Filter.POPULARITY.desc()

    }


}