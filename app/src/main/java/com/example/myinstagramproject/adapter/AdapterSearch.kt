package com.example.myinstagramproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.myinstagramproject.R

class AdapterSearch(private val list: MutableList<Int>): RecyclerView.Adapter<AdapterSearch.listViewHolder>() {
    class listViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val video: ImageView = view.findViewById(R.id.video_search)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_search, parent, false)
        return listViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: listViewHolder, position: Int) {
        val search = list[position]
        holder.video.setImageResource(search)
    }
}