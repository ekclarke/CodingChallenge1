package com.example.challenge1

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GuideViewModel(activity: AppCompatActivity) {

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
        //adapter can use this to pull image from URL




}