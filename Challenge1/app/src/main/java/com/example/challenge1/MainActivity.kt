package com.example.challenge1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.challenge1.databinding.ActivityMainBinding
import kotlin.concurrent.thread

//program flow/architecture:
//MainActivity binds RecyclerView
//MainActivity calls ViewModel to retrieve data
//ViewModel calls GetJSONData (OR REPOSITORY)
//JSON data retrieved by GetJSONData using RawData
//RawData pulls data from URL and passes it up via JSON to ViewModel
//ViewModel updates LiveData
//MainActivity observes LiveData
//MainActivity updates Adapter with data to be displayed in RecyclerView

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
        adapter = GuideAdapter(guideList)
        recyclerView.adapter=adapter

        val getRawData = GetRawData(this)
        val getJSONData = GetJSONData(this)

        //change this so the background processing is happening via a function in viewModel and actual threading is in GetRawData
        thread {
            guideList=getJSONData.processJSON(getRawData.getFromURL(dataUrl))
        }

        //liveDataRefresh(guideList)
    }

    //don't do this here - do it in the adapter
//    fun liveDataRefresh(data: List<Guide>){
//        guideViewModel.name.observe(this) {
//        binding.nameView.text=it
//                }
//        guideViewModel.startDate.observe(this){
//            binding.startView.text=it
//        }
//    }


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