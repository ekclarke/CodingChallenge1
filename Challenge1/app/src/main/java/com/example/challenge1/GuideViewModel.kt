package com.example.challenge1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class GuideViewModel() : ViewModel(){
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

    fun retrieveData(url: String): List<Guide> {
        Log.d(TAG, "retrieveData called")
        val getJSONData = GetJSONData()

        thread {
            _guideList.value = getJSONData.requestJSON(url)
        }
        //not sure !! is safe - think about it
        if (_guideList.value!!.isNotEmpty()){
            _name.value = "No items found"
            _startDate.value = "n/a"
        }
    return _guideList.value as List<Guide>
    }

}