package com.example.movieappactivity.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.movieappactivity.model.Movie
import com.example.movieappactivity.service.MovieDBService
import java.text.SimpleDateFormat

class MovieDBRepository(private val movieDBService: MovieDBService) {
    @SuppressLint("SimpleDateFormat")
    suspend fun getAllMovie(page: Int = 1): List<Movie> {
        val ListRawMovie = movieDBService.getAllMovie().results
        val data = mutableListOf<Movie>()
        for (rawMovie in ListRawMovie) {
            val movie = Movie(
                rawMovie.id,
                rawMovie.overview,
                rawMovie.poster_path,
                SimpleDateFormat("yyyy-MM-dd").parse(rawMovie.release_date)!!,
                rawMovie.title,
                rawMovie.vote_average,
                rawMovie.vote_count
            )
            data.add(movie)
        }
        return data
    }

    @SuppressLint("SimpleDateFormat")
    suspend fun getMovieDetail(movieID: Int): Movie {
        val rawMovie = movieDBService.getMovieDetail(movieID)
        Log.d("Test123", rawMovie.toString()) // check debugging
        val data = Movie(
            rawMovie.id,
            rawMovie.overview,
            rawMovie.poster_path,
            SimpleDateFormat("yyyy-MM-dd").parse(rawMovie.release_date)!!,
            rawMovie.title,
            rawMovie.vote_average,
            rawMovie.vote_count
        )
        return data
    }
}
