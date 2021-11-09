package com.example.challenge1

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GuideAdapter:
    RecyclerView.Adapter<GuideAdapter.GuideViewHolder>() {
    private val TAG = "GuideAdapter"
    private val guideList: MutableList<Guide> = mutableListOf()

    class GuideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name_view)
        val startView: TextView = view.findViewById(R.id.start_view)
    }

    //DO NOT put the recyclerview here - put the list item layout!
    //view holders hold your view (item layout) and the data for that view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
        Log.d(TAG, ".onCreateViewHolder new view requested")
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.group_item, parent, false)
        return GuideViewHolder(adapterLayout)
    }

    //best practice to not do empty states in adapters - best to handle with viewModel
    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder called")
            val guideItem = guideList[position]
        Log.d(TAG, guideItem.name)
            holder.nameView.text = guideItem.name
            holder.startView.text = guideItem.startDate
         }

    override fun getItemCount()=guideList.size

    fun update(newList: List<Guide>){
        Log.d(TAG, "update called")
        if(newList.isNotEmpty()) {
            guideList.clear()
            guideList.addAll(newList)
            notifyDataSetChanged()
        }
    }

}