package com.example.challenge1

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import okio.BufferedSource

@JsonClass(generateAdapter = true)
data class Wrapper(@Json(name = "data") val list: List<Guide>) {
    companion object {
        fun processJSONtoList(downloadedData: BufferedSource): List<Guide> {
            val adapter = MoshiHelper.moshi.adapter(Wrapper::class.java)
            val guideList = adapter.fromJson(downloadedData)!!.list
            return guideList!!
        }
    }
}
