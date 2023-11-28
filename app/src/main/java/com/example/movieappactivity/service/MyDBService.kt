package com.example.movieappactivity.service

import com.example.movieappactivity.model.APIResponse
import com.example.movieappactivity.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface MyDBService {
    @POST("login")
    suspend fun login(@Body user: User): APIResponse

    @DELETE("logout")
    suspend fun logout(): APIResponse

    @POST("create_user")
    suspend fun register(@Body user: User): APIResponse
}