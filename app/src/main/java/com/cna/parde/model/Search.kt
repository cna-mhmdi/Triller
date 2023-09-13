package com.cna.parde.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class SearchResponse(
    val page: Int,
    val results: List<Search>
)

@Parcelize
data class Search(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val id: Int = 0,
    val name : String = "",
    val title: String = "",
    val original_language: String = "",
    val original_name: String = "",
    val original_title: String = "",
    val overview: String = "",
    val poster_path: String = "",
    val media_type: String = "",
    val popularity: Float = 0f,
    val release_date: String = "",
    val first_air_date: String = "" ,
    val video: Boolean = false,
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
):Parcelable
