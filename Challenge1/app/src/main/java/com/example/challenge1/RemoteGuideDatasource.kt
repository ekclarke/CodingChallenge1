package com.example.challenge1

import android.util.Log
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.security.InvalidParameterException

//Deliverable one

class RemoteGuideDatasource {
    private val TAG = "RemoteGuideDatasource"

    fun getFromURL(url: String, callback: Callback) {
        Log.d(TAG, "getFromURL called")
        if (url == "") {
            throw InvalidParameterException("No URL passed.")
        } else {
            var client = OkHttpClient()
            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)
            client = client.newBuilder().addInterceptor(logging).build()
            val request = Request.Builder().url(url).build()
            client.newCall(request).enqueue(callback)
        }
    }
}