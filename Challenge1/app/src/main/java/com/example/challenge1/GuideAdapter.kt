package com.example.challenge1

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GuideAdapter(private var guideList: List<Guide>) :
    RecyclerView.Adapter<GuideAdapter.GuideViewHolder>() {
    private val TAG = "GuideAdapter"

    class GuideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name_view)
        val startView: TextView = view.findViewById(R.id.start_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
        Log.d(TAG, ".onCreateViewHolder new view requested")
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
        return GuideViewHolder(view)
    }

    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
        if (guideList.isEmpty()) {
            holder.nameView.setText("No items found.")
            holder.startView.setText("n/a")
        } else {
            val guideItem = guideList[position]
            holder.nameView.text = guideItem.name
            holder.startView.text = guideItem.startDate

        }
    }

    override fun getItemCount(): Int {
        return if (guideList.isNotEmpty()) guideList.size else 1
    }

    fun getData(position: Int): Guide? {
        return if (guideList.isNotEmpty()) guideList[position] else null
    }

}