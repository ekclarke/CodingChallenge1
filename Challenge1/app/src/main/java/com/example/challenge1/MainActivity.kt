package com.example.challenge1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.challenge1.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

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
class MainActivity : AppCompatActivity() {
    private val guideViewModel: GuideViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GuideAdapter
    private lateinit var guideList: List<Guide>

    private val TAG = "MainActivity"
    private val dataUrl = "https://www.guidebook.com/service/v2/upcomingGuides/"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate called")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.guideRecyclerView
        //do recyclerviews need layout managers?
        Log.d(TAG, "recyclerView bound")
        adapter = GuideAdapter()
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        Log.d(TAG, "adapter and layout manager assigned")

        refreshData()
    }

    private fun refreshData() {
        Log.d(TAG, "ViewModel called")
        guideList = guideViewModel.retrieveData(dataUrl)
        adapter.update(guideList)
    }

}