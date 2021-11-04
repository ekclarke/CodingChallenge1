package com.example.challenge1

import android.util.Log
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

//Deliverable one

enum class DownloadStatus {
    OK, IDLE, NOT_INITIALISED, FAILED_OR_EMPTY, PERMISSIONS_ERROR, ERROR
}
class GetRawData(private val listener: GetJSONData) : ArrayList<String>() {
    private val TAG = "GetRawData"
    private var downloadStatus = DownloadStatus.IDLE

    interface OnDownloadComplete {
        fun onDownloadComplete(data: String, status: DownloadStatus)
    }

    fun getFromURL(vararg params: String?): String {
        if (params[0] == null) {
            downloadStatus = DownloadStatus.NOT_INITIALISED
            return "No URL specified"
        }

        try {
            downloadStatus = DownloadStatus.OK
            val rawData = URL(params[0]).readText()
            Log.d(TAG, rawData)
            return rawData
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is MalformedURLException -> {
                    downloadStatus = DownloadStatus.NOT_INITIALISED
                    "doInBackground: Invalid URL ${e.message}"
                }
                is IOException -> {
                    downloadStatus = DownloadStatus.FAILED_OR_EMPTY
                    "doInBackground: IO Exception reading data: ${e.message}"
                }
                is SecurityException -> {
                    downloadStatus = DownloadStatus.PERMISSIONS_ERROR
                    "doInBackground: Security exception: Needs permission? ${e.message}"
                } else -> {
                    downloadStatus = DownloadStatus.ERROR
                    "Unknown error: ${e.message}"
                }
            }
            Log.e(TAG, errorMessage)

            return errorMessage
        }
    }



}