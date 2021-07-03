package com.lontongmalam.clubsinbundesliga2020

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterViewList(private val listItem: ArrayList<ClubDetails>) : RecyclerView.Adapter<AdapterViewList.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDesc: TextView = itemView.findViewById(R.id.tv_item_desc)
        var imgLogo: ImageView = itemView.findViewById(R.id.img_item_logo)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_view, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = listItem[position]
        Glide.with(holder.itemView.context)
                .load(item.logo)
                .into(holder.imgLogo)
        holder.tvName.text = item.name
        holder.tvDesc.text = item.desc
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listItem[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ClubDetails)
    }

}