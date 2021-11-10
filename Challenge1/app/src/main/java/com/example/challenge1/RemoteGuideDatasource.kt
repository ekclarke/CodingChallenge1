package com.example.challenge1

import android.util.Log
import okhttp3.*
import java.security.InvalidParameterException


//Deliverable one

class RemoteGuideDatasource(private val listener: GuideRepository){
    private val TAG = "GetRawData"

    fun getFromURL(url: String, callback: Callback){
        Log.d(TAG, "getFromURL called")
        if (url == "") {
            throw InvalidParameterException("No URL passed")
        } else {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            client.newCall(request).enqueue(callback)
        }
    }
}