package com.keepcoding.themoviedb.di

import android.content.Context
import androidx.room.Room
import com.keepcoding.themoviedb.data.local.MovieDatabase
import com.keepcoding.themoviedb.data.local.MoviesDao
import com.keepcoding.themoviedb.data.remote.TheMovieDbAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val originalUrl = request.url

                val newUrl = originalUrl.newBuilder()
                    .addQueryParameter("api_key", "20985cf66e1756b5eea015b67a341389").build()

                val newRequest =
                    request.newBuilder().url(newUrl).header("User-Agent", "OkHttp Headers.java")
                        .build()

                chain.proceed(newRequest)
            }
            .addInterceptor(
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun providesMovieAPI(retrofit: Retrofit): TheMovieDbAPI {
        return retrofit.create(TheMovieDbAPI::class.java)
    }

    @Provides
    fun providesMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "themoviedb_database"
        ).build()
    }

    @Provides
    fun providesMovieDao(movieDatabase: MovieDatabase): MoviesDao {
        return movieDatabase.movieDao()
    }
}