package com.example.movieappactivity.repository

import com.example.movieappactivity.service.MovieDBService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit // Retrieving and uploading data via a REST-based web service

class AuthInterceptor(private val bearerToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .header("Authorization", "Bearer $bearerToken")
            .build()
        return chain.proceed(request)
    }
}
class MovieDBContainer { // Accessibility

    companion object{ // Basically like static function
        val BASE_IMG = "https://image.tmdb.org/t/p/w500"
    }

    private val BASE_URL = "https://api.themoviedb.org/3/movie/"
    private val ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1ZGM0NWIxM2I3ZjMyMjczM2ZmY2FiOWI4NTc4NTI4YiIsInN1YiI6IjY1Mzg2ZjliN2ZjYWIzMDBjOTg2OTgyOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4q6j7CIN2IEnl2XRFycGjcbYKjmBGSLHL9IEoxHThcg"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(ACCESS_TOKEN))
        .build()

    private val retrofit =  Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    private val retrofitService: MovieDBService by lazy{
        retrofit.create(MovieDBService::class.java)
    }

    val movieDBRepository: MovieDBRepository by lazy{
        MovieDBRepository(retrofitService)
    }
}