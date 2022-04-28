package com.keepcoding.themoviedb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "movies")
data class MovieOriginal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uid") val uid: Int? = -1,
    @ColumnInfo(name = "title") @Json(name = "title") val title: String = "",
    @ColumnInfo(name = "popularity") @Json(name = "popularity") val popularity: Double = 0.0,
    @ColumnInfo(name = "poster_path") @Json(name = "poster_path") val posterPath: String = "",
    @ColumnInfo(name = "genre_ids") @Json(name = "genre_ids") val genreIds: List<Int> = listOf(),
    @ColumnInfo(name = "vote_count") @Json(name = "vote_count") val voteCount: Int = 0,
)


// -------------------- //


data class MovieNetwork(
    @Json(name = "title") val title: String = "",
    @Json(name = "popularity") val popularity: Double = 0.0,
    @Json(name = "poster_path") val posterPath: String = "",
    @Json(name = "genre_ids") val genreIds: List<Int> = listOf(),
    @Json(name = "vote_count") val voteCount: Int = 0,
)

@Entity(tableName = "movies")
data class MovieLocal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uid") val uid: Int?,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "popularity") val popularity: Double = 0.0,
    @ColumnInfo(name = "poster_path") val posterPath: String = "",
    @ColumnInfo(name = "vote_count") val voteCount: Int = 0,
)

data class MoviePresentation(
    val title: String = "",
    val posterPath: String = "",
    val voteCount: Int = 0,
)
