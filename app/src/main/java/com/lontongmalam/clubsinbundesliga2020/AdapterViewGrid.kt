package com.lontongmalam.clubsinbundesliga2020

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterViewGrid(private val listItem: ArrayList<ClubDetails>) : RecyclerView.Adapter<AdapterViewGrid.GridViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var imgLogo: ImageView = itemView.findViewById(R.id.img_item_logo)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GridViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_grid_view, viewGroup, false)
        return GridViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(listItem[position].logo)
            .into(holder.imgLogo)
        holder.tvName.text = listItem[position].name
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listItem[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ClubDetails)
    }
}