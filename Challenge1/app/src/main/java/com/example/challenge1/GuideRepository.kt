package com.example.challenge1

import android.util.Log
import okhttp3.Call
import okhttp3.Response
import java.io.IOException

//Deliverable two

class GuideRepository {
    private val TAG = "GuideRepository"

    interface Callback {
        fun onComplete(pulledList: List<Guide>)
    }

    fun getGuides(url: String, callback: Callback) {
        Log.d(TAG, "getGuides called")
        val getRawData = RemoteGuideDatasource()
        val httpCallback = object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) throw IOException("Unsuccessful response.")
                callback.onComplete(Wrapper.processJSONtoList(response.body!!.source()))
                response.close()
            }
        }
        getRawData.getFromURL(url, httpCallback)
    }
}