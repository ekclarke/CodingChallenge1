@file:Suppress("PrivatePropertyName")

package com.example.challenge1

import android.util.Log
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

//Deliverable two
//update with moshi!


class GetJSONData {
    private val TAG = "GetJSONData"

    interface Callback {
        fun onComplete(pulledList: List<Guide>)
    }

    fun requestJSON(url: String, callback: Callback) {
        Log.d(TAG, "requestJSON called")
            val getRawData = GetRawData(this)
            val rawData = getRawData.getFromURL(url)
            callback.onComplete(processJSON(rawData))
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