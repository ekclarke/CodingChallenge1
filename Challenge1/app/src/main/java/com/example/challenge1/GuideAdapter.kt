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
    private var guideList: List<Guide> = listOf()

    class GuideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name_view)
        val startView: TextView = view.findViewById(R.id.start_view)
    }

    //DO NOT put the recyclerview here - put the list item layout!
    //view holders hold your view (item layout) and the data for that view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
        Log.d(TAG, ".onCreateViewHolder new view requested")
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)
        return GuideViewHolder(adapterLayout)
    }

    //best practice to not do empty states in adapters - best to handle with viewModel
    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
        if (guideList.isEmpty()) {
            holder.nameView.text = "No items found."
            holder.startView.text = "n/a"
        } else {
            val guideItem = guideList[position]
            holder.nameView.text = guideItem.name
            holder.startView.text = guideItem.startDate

        }
    }

    override fun getItemCount(): Int {
        return guideList.size
    }

    fun loadNewData(newList: List<Guide>){
        guideList=newList
        notifyDataSetChanged()
    }

    fun getData(position: Int): Guide? {
        return if (guideList.isNotEmpty()) guideList[position] else null
    }


}