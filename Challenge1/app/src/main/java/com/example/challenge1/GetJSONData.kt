package com.example.challenge1

import android.util.Log
import org.json.JSONException
import org.json.JSONObject

//Deliverable two

class GetJSONData(private val listener: OnDataAvailable) : ArrayList<Guide>() {
    private val TAG = "GetJSONData"

    interface OnDataAvailable {
        fun onDataAvailable(data: List<Guide>)
        fun onError(exception: Exception)
    }

    fun getJSON(url: String): List<Guide>{
        val getRawData = GetRawData(this)
        val rawData = getRawData.getFromURL(url)
        return processJSON(rawData)
    }

      private fun processJSON(vararg params: String): List<Guide> {
        Log.d(TAG, "retrieveData starts")

        val guideList = ArrayList<Guide>()

        try {
            //best practice is to make raw data retrieval its own class and then return it here
            val jsonData = JSONObject(params[0])
            val dataArray = jsonData.getJSONArray("data")

            for (i in 0 until dataArray.length()) {
                val jsonItem = dataArray.getJSONObject(i)
                val startDate = jsonItem.getString("startDate")
                val endDate = jsonItem.getString("endDate")
                val name = jsonItem.getString("name")
                val url = jsonItem.getString("url")
                val venue = jsonItem.getString("venue")
                val icon = jsonItem.getString("icon")

                val guideObject = Guide(startDate, endDate, name, url, venue, icon)

                guideList.add(guideObject)
                Log.d(TAG, "Start:$startDate")
                Log.d(TAG, "End:$endDate")
                Log.d(TAG, "Name:$name")
                Log.d(TAG, "URL:$url")
                Log.d(TAG, "Venue:$venue")
                Log.d(TAG, "Icon:$icon")

                Log.d(TAG, ".retrieveData $guideObject")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e(TAG, ".retrieveData: Error processing Json data. ${e.message}")
            listener.onError(e)
        }

        Log.d(TAG, ".retrieveData ends")
        return guideList
     }
}