package com.demo.leanagri.data.model.list

import com.demo.leanagri.data.model.BaseResponse
import com.demo.leanagri.data.model.detail.MovieDetail
import com.google.gson.annotations.SerializedName

data class MoviesListResponse (
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<MovieDetail>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
): BaseResponse()