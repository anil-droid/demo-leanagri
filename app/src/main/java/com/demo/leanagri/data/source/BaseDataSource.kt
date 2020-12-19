package com.demo.leanagri.data.source

import android.util.Log
import retrofit2.Response
import com.demo.leanagri.data.APIResponse

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): APIResponse<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return APIResponse.Success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): APIResponse.Failure<T> {
        Log.e("remoteDataSource", message)
        return APIResponse.Failure("Network call has failed for a following reason: $message")
    }

}