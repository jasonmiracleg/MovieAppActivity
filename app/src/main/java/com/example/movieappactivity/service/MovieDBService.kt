package com.example.movieappactivity.service

import com.example.movieappactivity.model.NowPlaying
import com.example.movieappactivity.model.RawMovie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBService { // Manage the API Services - use end point
    @GET("now_playing")
    suspend fun getAllMovie( // just search the query instruction from the API docs
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"
    ): NowPlaying

    @GET("{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieID: Int /*this is a variable, you can modify it*/): RawMovie
}