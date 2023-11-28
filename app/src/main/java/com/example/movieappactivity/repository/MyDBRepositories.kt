package com.example.movieappactivity.repository

import com.example.movieappactivity.model.User
import com.example.movieappactivity.service.MyDBService
import java.net.HttpURLConnection

class MyDBRepositories(private val myDBService: MyDBService) {
    suspend fun login(
        email: String,
        password: String
    ): String { // if email and password are managed in view model, then this function will retrieve the user model
        val user = User(email = email, password = password)
        val result = myDBService.login(user)
        if (result.status.toInt() == HttpURLConnection.HTTP_OK) {
            return result.data as String
        }
        return result.message
    }

    suspend fun logout() : String {
        val result = myDBService.logout()
        return result.message
    }

    suspend fun register(user: User): String {
        val result = myDBService.register(user)
        if (result.status.toInt() == HttpURLConnection.HTTP_OK) {
            return result.data as String
        }
        return result.message
    }
}