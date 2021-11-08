package com.example.challenge1

import android.util.Log
import okhttp3.*
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL


//Deliverable one
//Update with okhttp!

class GetRawData(private val listener: GetJSONData) : ArrayList<String>() {
    private val TAG = "GetRawData"
    private var rawDataOutput = ""

    fun getFromURL(vararg params: String?): String{

        Log.d(TAG, "getFromURL called")
        if (params[0] == null) {
            return "No URL specified"
        } else {
            val client = OkHttpClient()
            val request = Request.Builder().url(params[0]!!).build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful) throw IOException("oops")

                        rawDataOutput    = response.body!!.string()
                    }
                }

            })

        }
    return rawDataOutput
    }
}