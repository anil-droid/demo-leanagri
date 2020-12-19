package com.demo.leanagri.data.model.detail

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.demo.leanagri.core.Constants
import com.demo.leanagri.data.db.AppConverters
import com.demo.leanagri.data.model.BaseResponse
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap

@Entity(tableName = Constants.DB.Table.Movie.NAME,  indices = [Index(value = ["id"], unique = true)])
data class MovieDetail (
    @SerializedName("id")
    @PrimaryKey
    val id: Int?,
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("budget")
    val budget: Int?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("imdb_id")
    val imdbId: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("revenue")
    val revenue: Int?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("genres")
    @TypeConverters(AppConverters::class)
    val genres: ArrayList<LinkedTreeMap<Any, Any>>?,
    @SerializedName("production_companies")
    @TypeConverters(AppConverters::class)
    val productionCompanies: ArrayList<LinkedTreeMap<Any, Any>>?,
    @SerializedName("production_countries")
    @TypeConverters(AppConverters::class)
    val productionCountries: ArrayList<LinkedTreeMap<Any, Any>>?,
    @SerializedName("spoken_languages")
    @TypeConverters(AppConverters::class)
    val spokenLanguages: ArrayList<LinkedTreeMap<Any, Any>>?,
): BaseResponse()
