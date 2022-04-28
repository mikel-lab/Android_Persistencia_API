package com.keepcoding.themoviedb.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.keepcoding.themoviedb.model.MovieLocal

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<MovieLocal>

    @Query("SELECT * FROM movies WHERE uid = :id")
    fun getMovieById(id: Int): MovieLocal

    @Insert
    fun insert(movieLocal: MovieLocal)

    @Delete
    fun delete(movieLocal: MovieLocal)
}