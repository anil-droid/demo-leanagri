package com.demo.leanagri.data


sealed class APIResponse<out T>
{
    data class Success<out R>(val data: R?): APIResponse<R>()
    data class Failure<out R>(val errorMessage: String?): APIResponse<R>()

}
