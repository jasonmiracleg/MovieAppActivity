package com.example.movieappactivity.repository

import com.example.movieappactivity.service.MovieDBService
import com.example.movieappactivity.service.MyDBService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyDBContainer {
    companion object{ // Basically like static function
        val BASE_IMG = ""
        var ACCESS_TOKEN = ""
    }

    private val BASE_URL = "http://192.168.174.233/laravel-projects/visprog/public/api/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(ACCESS_TOKEN))
        .build()

    private val retrofit =  Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
//        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    private val retrofitService: MyDBService by lazy{
        retrofit.create(MyDBService::class.java)
    }

    val movieDBRepository: MyDBRepositories by lazy{
        MyDBRepositories(retrofitService)
    }
}