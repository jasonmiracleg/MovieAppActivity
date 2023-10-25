package com.example.movieappactivity.model

import kotlinx.serialization.Serializable

@Serializable // Convert Json ke Object
data class Dates(
    val maximum: String,
    val minimum: String
)