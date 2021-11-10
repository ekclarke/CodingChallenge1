@file:Suppress("PrivatePropertyName")

package com.example.challenge1

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.Call
import okhttp3.Response
import java.io.IOException

//Deliverable two

class GuideRepository {
    private val TAG = "GetJSONData"

    interface Callback {
        fun onComplete(pulledList: List<Guide>)
    }

    fun requestJSON(url: String, callback: Callback) {
        Log.d(TAG, "requestJSON called")
        val getRawData = RemoteGuideDatasource(this)
        val httpCallback = object : okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) throw IOException("oops")
                Log.d(TAG, response.body!!.string())
                callback.onComplete(processJSON(response.body!!.string()))
                response.close()
            }
        }
       getRawData.getFromURL(url, httpCallback)
    }

    private fun processJSON(downloadedData: String): List<Guide> {
        Log.d(TAG, "processJSON starts")
        Log.d(TAG, downloadedData)
        val type = Types.newParameterizedType(List::class.java, Guide::class.java)
        val moshi: Moshi = Moshi.Builder().build()
        val adapter = moshi.adapter<List<Guide>>(type)
        val guideList = adapter.fromJson(downloadedData)
        Log.d(TAG, ".retrieveData ends")
        return guideList!!
    }
}