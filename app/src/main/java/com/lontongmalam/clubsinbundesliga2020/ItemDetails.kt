package com.lontongmalam.clubsinbundesliga2020

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ItemDetails() : AppCompatActivity() {
    private var list: ArrayList<ClubDetails> = arrayListOf()
    companion object {
        const val EXTRA_BOOL = "extra_bool"
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)
        window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        var actionBar = getSupportActionBar()

        if (actionBar != null) {

            // Customize the back button
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_selector)

            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        supportActionBar?.title = ""
        //supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(android.R.color.transparent)))
        //pgeLay.setBackgroundColor(this.resources.getColor(android.R.color.black))
        list.addAll(ClubData.listData)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val item = list[id]
        val name = item.name
        val desc = item.desc
        val logo = item.logo
        val stad_name = item.stad_name
        val stad_desc = item.stad_desc
        val stad_loc = item.stad_loc
        val stad_geo = item.stad_geo
        val stad_img = item.stad_img

        val tvName: TextView = findViewById(R.id.tv_item_name)
        val tvDesc: TextView = findViewById(R.id.tv_item_desc)
        val imgLogo: ImageView = findViewById(R.id.img_item_logo)
        val tvStadName: TextView = findViewById(R.id.tv_item_stad_name)
        val tvStadDesc: TextView = findViewById(R.id.tv_item_stad_desc)
        val tvStadLoc: TextView = findViewById(R.id.tv_item_stad_loc)
        val imgStadImg: ImageView = findViewById(R.id.img_item_stad_img)
        var btnStadGeo: ImageButton = findViewById(R.id.btn_item_stad_geo)
        val pgeLay: ScrollView = findViewById(R.id.sv_item_details)

        /*

        val bundle = intent.extras
        val name = bundle?.getString("name")
        val desc = bundle?.getString("desc")
        val logo = bundle?.getInt("logo")
        val stad_name = bundle?.getString("stad_name")
        val stad_desc = bundle?.getString("stad_desc")
        val stad_loc = bundle?.getString("stad_loc")
        val stad_geo = bundle?.getString("stad_geo")
        val stad_img = bundle?.getInt("stad_img")
        val priColor: Int = Color.parseColor("#FF54B948")
        val secColor: Int = Color.parseColor("#FFFFFFFF")
        val triColor: Int = Color.parseColor("#FF000000")

        supportActionBar?.setBackgroundDrawable(ColorDrawable(secColor))
        pgeLay.setBackgroundColor(secColor)
        tvName.setTextColor(priColor)
        tvStadName.setTextColor(priColor)
        tvDesc.setTextColor(triColor)
        tvStadDesc.setTextColor(triColor)
        tvStadLoc.setTextColor(triColor)
        */

        tvName.text = name
        tvDesc.text = desc
        tvStadName.text = stad_name
        tvStadDesc.text = stad_desc
        tvStadLoc.text = stad_loc
        imgLogo.setImageResource(logo!!)
        imgStadImg.setImageResource(stad_img!!)

        btnStadGeo.setOnClickListener {
            val showMap = Intent(Intent.ACTION_VIEW, Uri.parse(stad_geo))
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(showMap)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.share_favorite_menu, menu)
        val stat_pre = intent.getBooleanExtra(EXTRA_BOOL, false)
        val item = menu.findItem(R.id.item_favorite)
        if(stat_pre){
            item.setIcon(R.drawable.ic_baseline_star_pressed_36)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = intent.getIntExtra(EXTRA_ID, 0)
        return when (item.itemId) {
            R.id.item_favorite -> {
                var stat_pre = list[id].stat_pre
                stat_pre = !stat_pre
                list[id].stat_pre = stat_pre
                if(stat_pre){
                    item.setIcon(R.drawable.ic_baseline_star_pressed_36)
                    Toast.makeText(this,"Marked as favorite", Toast.LENGTH_SHORT).show()
                }else{
                    item.setIcon(R.drawable.ic_baseline_star_36)
                    Toast.makeText(this,"Removed from favorite", Toast.LENGTH_SHORT).show()
                }
                true
            }
            R.id.item_share -> {
                val shareData = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, intent.extras?.getString("name"))
                    putExtra(Intent.EXTRA_TEXT, intent.extras?.getString("desc"))
                }
                if (shareData.resolveActivity(packageManager) != null) {
                    startActivity(Intent.createChooser(shareData, "Share via.."))
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}