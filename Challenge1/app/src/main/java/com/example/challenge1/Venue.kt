package com.example.challenge1

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Venue(
    val city: String = "",
    val state: String = ""
)
