package com.cna.parde.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

interface DisplayableItem {
    val movieTitle: String
    val voteAverage: Float
    val posterPath: String
}

data class NPMoviesResponse(
    val page: Int,
    val results: List<NPMovie>,
)

@Parcelize
data class NPMovie(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val id: Int = 0,
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
) : Parcelable, DisplayableItem {
    override val movieTitle: String
        get() = title

    override val voteAverage: Float
        get() = vote_average

    override val posterPath: String
        get() = poster_path
}

data class POPMoviesResponse(
    val page: Int,
    val results: List<POPMovie>,
)

@Parcelize
data class POPMovie(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val id: Int = 0,
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
) : Parcelable, DisplayableItem {
    override val movieTitle: String
        get() = title

    override val voteAverage: Float
        get() = vote_average

    override val posterPath: String
        get() = poster_path
}


data class UcMovieResponse(
    val page: Int,
    val results: List<UCMovie>,
)

@Parcelize
data class UCMovie(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val id: Int = 0,
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
) : Parcelable, DisplayableItem {
    override val movieTitle: String
        get() = title

    override val voteAverage: Float
        get() = vote_average

    override val posterPath: String
        get() = poster_path
}

data class TRMovieResponse(
    val page: Int,
    val results: List<TRMovie>,
)

@Parcelize
data class TRMovie(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val id: Int = 0,
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
) : Parcelable, DisplayableItem {
    override val movieTitle: String
        get() = title

    override val voteAverage: Float
        get() = vote_average

    override val posterPath: String
        get() = poster_path
}

data class TMovieResponse(
    val page: Int,
    val results: List<TMovie>,
)

@Parcelize
data class TMovie(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val id: Int = 0,
    val title: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val poster_path: String = "",
    val popularity: Float = 0f,
    val release_date: String = "",
    val video: Boolean = false,
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
) : Parcelable, DisplayableItem {
    override val movieTitle: String
        get() = title

    override val voteAverage: Float
        get() = vote_average

    override val posterPath: String
        get() = poster_path
}


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
) : Parcelable, DisplayableItem {
    override val movieTitle: String
        get() = name

    override val voteAverage: Float
        get() = vote_average

    override val posterPath: String
        get() = poster_path
}

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
) : Parcelable, DisplayableItem {
    override val movieTitle: String
        get() = name

    override val voteAverage: Float
        get() = vote_average

    override val posterPath: String
        get() = poster_path

}


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
) : Parcelable, DisplayableItem {
    override val movieTitle: String
        get() = name

    override val voteAverage: Float
        get() = vote_average

    override val posterPath: String
        get() = poster_path
}

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
) : Parcelable, DisplayableItem {
    override val movieTitle: String
        get() = name

    override val voteAverage: Float
        get() = vote_average

    override val posterPath: String
        get() = poster_path
}


data class GMovieResponse(
    val page: Int,
    val results: List<GMovie>,
)

@Parcelize
data class GMovie(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val id: Int = 0,
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
) : Parcelable


data class DetailMovie(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val budget: Int = 0,
    val genres: List<GenreMovie> = emptyList(),
    val homepage: String = "",
    val id: Int = 0,
    val imdb_id: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
)

@Parcelize
data class GenreMovie(
    val id: Int = 0,
    val name: String = "",
) : Parcelable


data class CastMovieResponse(
    val id: Int = 0,
    val cast: List<Cast>,
)

@Parcelize
data class Cast(
    val adult: Boolean = false,
    val gender: Int = 0,
    val id: Int = 0,
    val known_for_department: String = "",
    val name: String = "",
    val original_name: String = "",
    val popularity: Float = 0f,
    val profile_path: String? = null,
    val cast_id: Int = 0,
    val character: String = "",
    val credit_id: String = "",
    val order: Int = 0,
) : Parcelable

data class RecMovieResponse(
    val page: Int = 0,
    val results: List<RecMovie>,
)

@Parcelize
data class RecMovie(
    val adult: Boolean = false,
    val backdrop_path: String? = null,
    val id: Int = 0,
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String? = null,
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Float = 0f,
    val vote_count: Int = 0,
) : Parcelable

