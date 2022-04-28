package com.keepcoding.themoviedb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keepcoding.themoviedb.model.MovieLocal


@Database(entities = [MovieLocal::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
}