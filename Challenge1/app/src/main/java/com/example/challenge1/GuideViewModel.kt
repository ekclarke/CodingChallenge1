package com.example.challenge1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GuideViewModel() : ViewModel() {
    private val TAG = "GuideViewModel"

    private val _startDate = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate

    private val _endDate = MutableLiveData<String>()
    val endDate: LiveData<String> = _endDate

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _url = MutableLiveData<String>()
    val url: LiveData<String> = _url

    private val _venue = MutableLiveData<String>()
    val venue: LiveData<String> = _venue

    private val _icon = MutableLiveData<String>()
    val icon: LiveData<String> = _icon

    private val _guideList = MutableLiveData<List<Guide>>()
    val guideList: LiveData<List<Guide>> = _guideList

    fun retrieveData(url: String) {
        Log.d(TAG, "retrieveData called")
        val getJSONData = GetJSONData()

        val callback = object : GetJSONData.Callback {
            override fun onComplete(pulledList: List<Guide>) {
                _guideList.postValue(pulledList)
                if (_guideList.value?.isEmpty() != false) {
                    _name.postValue("No items found")
                    _startDate.postValue( "n/a")
                }
            }
        }
        getJSONData.requestJSON(url, callback)

    }

}