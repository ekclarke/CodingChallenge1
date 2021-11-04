package com.example.challenge1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.challenge1.databinding.ActivityMainBinding
import androidx.activity.viewModels

//program flow/architecture:
//MainActivity binds RecyclerView
//MainActivity calls ViewModel to retrieve data
//ViewModel calls GetJSONData (OR REPOSITORY)
//JSON data retrieved by GetJSONData using RawData
//RawData pulls data from URL and passes it up via JSON to ViewModel
//ViewModel updates LiveData
//MainActivity observes LiveData
//MainActivity updates Adapter with data to be displayed in RecyclerView

//ViewModelProviders are deprecated but can still be used

//Configurations A and B
class MainActivity : AppCompatActivity(), GetJSONData.OnDataAvailable,GetRawData.OnDownloadComplete {
    private val guideViewModel: GuideViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GuideAdapter

    private val TAG = "MainActivity"
    private val dataUrl = "https://www.guidebook.com/service/v2/upcomingGuides/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.guideRecyclerView
        adapter = GuideAdapter()
        recyclerView.adapter = adapter

        guideViewModel.retrieveData(dataUrl)
    }

    override fun onDataAvailable(data: List<Guide>) {
        Log.d(TAG, "onDataAvailable called")
        adapter.loadNewData(data)

    }

    override fun onError(exception: Exception) {
        Log.d(TAG, "Error with JSON")
    }

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        Log.d(TAG, "onDownloadComplete called")
    }
}