package com.lontongmalam.clubsinbundesliga2020

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class AdapterViewCard(private val context: Context, private val listItem: ArrayList<ClubDetails>) : RecyclerView.Adapter<AdapterViewCard.CardViewViewHolder>() {
    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgLogo: ImageView = itemView.findViewById(R.id.img_item_logo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDesc: TextView = itemView.findViewById(R.id.tv_item_desc)
        var btnFavorite: ImageButton = itemView.findViewById(R.id.btn_set_favorite)
        var btnShare: ImageButton = itemView.findViewById(R.id.btn_set_share)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card_view, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val item = listItem[position]
        Glide.with(holder.itemView.context)
                .load(item.logo)
                .into(holder.imgLogo)
        holder.tvName.text = item.name
        holder.tvDesc.text = item.desc

        holder.btnFavorite.setOnClickListener {
            item.stat_pre=!item.stat_pre
            if(item.stat_pre){
                holder.btnFavorite.setImageResource(R.drawable.ic_baseline_star_pressed_36)
                Toast.makeText(holder.itemView.context, "Marked as favorite", Toast.LENGTH_SHORT).show()
            }else{
                holder.btnFavorite.setImageResource(R.drawable.ic_baseline_star_36)
                Toast.makeText(holder.itemView.context,"Removed from favorite", Toast.LENGTH_SHORT).show()
            }

        }

        /*
        (holder.btnFavorite as ImageButton).setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val view = v as ImageButton
                    view.background.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP)
                    v.invalidate()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    val view = v as ImageButton
                    view.background.clearColorFilter()
                    view.invalidate()
                }
            }
            true
        }
        */

        holder.btnShare.setOnClickListener {
            val shareData = Intent(Intent.ACTION_SEND)
            shareData.setType("text/plain")
            shareData.putExtra(Intent.EXTRA_SUBJECT, item.name)
            shareData.putExtra(Intent.EXTRA_TEXT, item.desc)
            context.startActivity(Intent.createChooser(shareData, "Share via.."))
        }


        val data = Bundle()
        data.putString("name", item.name)
        data.putString("desc", item.desc)
        data.putInt("logo", item.logo)
        data.putString("stad_name", item.stad_name)
        data.putString("stad_desc", item.stad_desc)
        data.putString("stad_loc", item.stad_loc)
        data.putString("stad_geo", item.stad_geo)
        data.putInt("stad_img", item.stad_img)


        holder.itemView.setOnClickListener {
            val moveWithDataIntent = Intent(context, ItemDetails::class.java)
            moveWithDataIntent.putExtra(ItemDetails.EXTRA_ID, item.id)
            moveWithDataIntent.putExtra(ItemDetails.EXTRA_BOOL, listItem[position].stat_pre)
            moveWithDataIntent.putExtras(data)
            context.startActivity(moveWithDataIntent)
        }
    }

    override fun getItemCount(): Int {
        return listItem.size
    }
}