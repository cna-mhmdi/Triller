package com.cna.parde.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class GTvResponse(
    val page: Int,
    val results: List<GTv>,
)

@Parcelize
data class GTv(
    val backdrop_path: String = "",
    val id: Int = 0,
    val name: String = "",
    val original_language: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String = "",
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
) : Parcelable

data class DetailTvResponse(
    val backdrop_path: String = "",
    val first_air_date: String = "",
    val genres: List<GenreTv>,
    val id: Int = 0,
    val last_air_date: String = "",
    val name: String = "",
    val number_of_episodes: Int = 0,
    val number_of_seasons: Int = 0,
    val origin_country: List<String>,
    val original_language: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String = "",
    val status: String = "",
    val tagline: String = "",
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
)

data class GenreTv(
    val id: Int = 0,
    val name: String = "",
)



