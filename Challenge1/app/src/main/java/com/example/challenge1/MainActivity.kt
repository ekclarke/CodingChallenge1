package com.example.challenge1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.challenge1.databinding.ActivityMainBinding
import kotlin.concurrent.thread

//program flow/architecture:
//MainActivity inflates ViewModel
//ViewModel of simple RecyclerView with LiveData
//MainActivity requests data from ViewModel
//ViewModel passes to Adapter
//Adapter calls GetJSONData
//JSON data retrieved by GetJSONData using RawData
//RawData pulls data from URL


//Configurations A and B
class MainActivity : AppCompatActivity(), GetJSONData.OnDataAvailable,GetRawData.OnDownloadComplete {
    private lateinit var guideViewModel: GuideViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GuideAdapter
    private  var guideList: MutableList<Guide> = mutableListOf()


    private val TAG = "MainActivity"
    private val dataUrl= "https://www.guidebook.com/service/v2/upcomingGuides/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.guideRecyclerView
        guideViewModel = GuideViewModel(this)

        val getRawData = GetRawData(this)
        val getJSONData = GetJSONData(this)
        thread {
            guideList=getJSONData.processJSON(getRawData.getFromURL(dataUrl))
        }
        adapter = GuideAdapter(guideList)
        recyclerView.adapter=adapter
        liveDataRefresh(guideList)
    }

    fun liveDataRefresh(data: List<Guide>){
        guideViewModel.name.observe(this) {
        binding.nameView.text=it
                }
        guideViewModel.startDate.observe(this){
            binding.startView.text=it
        }
    }


    override fun onDataAvailable(data: List<Guide>) {
        Log.d(TAG, "onDataAvailable called")
    }

    override fun onError(exception: Exception) {
        Log.d(TAG, "Error with JSON")
    }

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        Log.d(TAG, "onDownloadComplete called")
    }
}