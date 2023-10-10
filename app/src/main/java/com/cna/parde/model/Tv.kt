package com.cna.parde.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class OTATvResponse(
    val page: Int,
    val results: List<OTATv>,
)

@Parcelize
data class OTATv(
    val backdrop_path: String = "",
    val first_air_date: String = "",
    val id: Int = 0,
    val name: String = "",
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String = "",
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
) : Parcelable

data class TTvResponse(
    val page: Int,
    val results: List<TTv>,
)

@Parcelize
data class TTv(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val id: Int = 0,
    val name: String = "",
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val poster_path: String = "",
    val popularity: Float = 0f,
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
) : Parcelable


data class POPTvResponse(
    val page: Int,
    val results: List<POPTv>,
)

@Parcelize
data class POPTv(
    val backdrop_path: String = "",
    val first_air_date: String = "",
    val id: Int = 0,
    val name: String = "",
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String = "",
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
) : Parcelable

data class TRTvResponse(
    val page: Int,
    val results: List<TRTv>,
)

@Parcelize
data class TRTv(
    val backdrop_path: String = "",
    val first_air_date: String = "",
    val id: Int = 0,
    val name: String = "",
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String = "",
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
) : Parcelable

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
    val original_name: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String = "",
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
) : Parcelable

data class DetailTvResponse(
    val backdrop_path: String = "",
    val first_air_date: String= "",
    val genres: List<GenreTv>,
    val id: Int = 0,
    val last_air_date: String = "",
    val name: String = "",
    val number_of_episodes: Int = 0,
    val number_of_seasons: Int = 0,
    val origin_country: List<String>,
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String = "",
    val status: String = "",
    val tagline: String = "",
    val vote_average: Float = 0f,
    val vote_count: Int =0
)

data class GenreTv(
    val id: Int = 0,
    val name: String = ""
)

data class CastTvResponse(
    val cast : List<CastTv>
)

@Parcelize
data class CastTv(
    val id: Int,
    val name: String,
    val original_name: String,
    val popularity: Float,
    val profile_path: String?,
):Parcelable

data class RecTvResponse(
    val page: Int = 0,
    val results: List<RecTv>,
)

@Parcelize
data class RecTv(
    val adult: Boolean = false,
    val backdrop_path: String? = null,
    val id: Int = 0,
    val original_language: String = "",
    val name: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String? = null,
    val release_date: String = "",
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
) : Parcelable

