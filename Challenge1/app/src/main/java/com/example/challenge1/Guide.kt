package com.example.challenge1

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Guide(
val startDate: String,
val endDate: String,
val name: String,
val url: String,
val venue: String,
val icon: String
)
