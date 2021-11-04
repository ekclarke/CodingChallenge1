package com.example.challenge1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class GuideViewModel : ViewModel(), GetJSONData.OnDataAvailable {

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

    fun retrieveData(url: String) {
        var guideList: List<Guide> = listOf()
        val getJSONData = GetJSONData(this)

        thread {
            guideList = getJSONData.getJSON(url)
        }
        updateVM(guideList)
    }

    private fun updateVM(data: List<Guide>) {
        //make sure this iterates appropriately
        for (i in data.indices) {
            _name.value = data[i].name!!
            _startDate.value = data[i].startDate!!
        }
    }

    override fun onDataAvailable(data: List<Guide>) {
        TODO("Not yet implemented")
    }

    override fun onError(exception: Exception) {
        TODO("Not yet implemented")
    }
}