package com.cna.parde.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class OTATvResponse(
    val page : Int,
    val results: List<OTATv>
)

@Parcelize
data class OTATv(
    val backdrop_path : String = "",
    val first_air_date: String = "",
    val id : Int = 0,
    val name: String = "",
    val original_language: String = "",
    val original_name : String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String = "",
    val vote_average: Float = 0f,
    val vote_count: Int = 0
):Parcelable
