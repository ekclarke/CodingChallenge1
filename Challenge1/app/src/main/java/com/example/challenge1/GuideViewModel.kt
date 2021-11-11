package com.example.challenge1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GuideViewModel() : ViewModel() {
    private val TAG = "GuideViewModel"

    private val _guideList = MutableLiveData<List<Guide>>()
    val guideList: LiveData<List<Guide>> = _guideList

    fun retrieveData(url: String) {
        Log.d(TAG, "retrieveData called")
        val getJSONData = GuideRepository()

        val callback = object : GuideRepository.Callback {
            override fun onComplete(pulledList: List<Guide>) {
                _guideList.postValue(pulledList)
            }
        }
        getJSONData.getGuides(url, callback)
    }
}