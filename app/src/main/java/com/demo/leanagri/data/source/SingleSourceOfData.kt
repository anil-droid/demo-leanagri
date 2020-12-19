package com.demo.leanagri.data.source

import com.demo.leanagri.data.model.detail.MovieDetail
import com.demo.leanagri.data.model.list.MoviesListResponse
import com.demo.leanagri.data.APIResponse
import com.demo.leanagri.data.APIResponse.Success
import com.demo.leanagri.data.APIResponse.Failure
import com.demo.leanagri.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


suspend fun getMoviesList(
    databaseQuery: () -> List<MovieDetail>,
    networkCall: suspend () -> APIResponse<MoviesListResponse>,
    saveCallResult: suspend (MoviesListResponse) -> Unit
) =
    flow {

        emit(Result.Loading)

        when (val responseStatus = networkCall.invoke()) {
            is Success -> {

                responseStatus.data?.results?.takeIf { it.isNotEmpty() }?.let {

                    emit(Result.Success(responseStatus.data.results))

                    saveCallResult(responseStatus.data)

                } ?: run {
                    emit(Result.Error("Data not found"))

                }
            }
            is Failure -> {

                emit(Result.Error( responseStatus.errorMessage ?: "Error!"))

                val source = databaseQuery.invoke()
                emit(Result.Success(source))
            }
        }

    }.flowOn(Dispatchers.IO)


fun <T> getMovieDetail(
    databaseQuery: () -> T,
    networkCall: suspend () -> APIResponse<T>,
    saveCallResult: suspend (T) -> Unit
) =
    flow {
        emit(Result.Loading)
        val source = databaseQuery.invoke()
        source?.let {
            emit(Result.Success(it))
        }

        when (val responseStatus = networkCall.invoke()) {
           is Success -> {

                responseStatus.data?.let {

                    emit(Result.Success(it))

                    saveCallResult(it)

                }
            }
            is Failure -> {

                emit(Result.Error( responseStatus.errorMessage ?: "Error!"))

            }
        }


    }.flowOn(Dispatchers.IO)




