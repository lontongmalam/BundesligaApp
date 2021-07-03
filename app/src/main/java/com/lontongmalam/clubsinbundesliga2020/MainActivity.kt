package com.lontongmalam.clubsinbundesliga2020

import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rv_Clubs: RecyclerView
    private var list: ArrayList<ClubDetails> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // Hide the status bar.
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //actionBar?.hide()
        //supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        //supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(android.R.color.transparent)))
        //supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.pattern_home_left))
        //supportActionBar?.setTitle(Html.fromHtml("<font color=\"red\">" + getString(R.string.app_name) + "</font>"))
        supportActionBar?.title = "Home"
        //if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
         //   val rv_Banner: ImageView = findViewById(R.id.img_banner_img)
        //    rv_Banner.setVisibility(ImageView.GONE)
        //}
        rv_Clubs = findViewById(R.id.rv_clubs)
        rv_Clubs.setHasFixedSize(true)

        list.addAll(ClubData.listData)
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }
    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                showRecyclerList()
            }
            R.id.action_grid -> {
                showRecyclerGrid()
            }
            R.id.action_cardview -> {
                showRecyclerCardView()
            }

            R.id.action_about -> {
                val moveIntent = Intent(this@MainActivity, AboutAuthor::class.java)
                startActivity(moveIntent)
            }
        }
    }

    private fun showRecyclerList() {
        rv_Clubs.layoutManager = LinearLayoutManager(this)
        val adapterViewList = AdapterViewList(list)
        rv_Clubs.adapter = adapterViewList

        adapterViewList.setOnItemClickCallback(object : AdapterViewList.OnItemClickCallback {
            override fun onItemClicked(data: ClubDetails) {
                showSelectedSite(data)
            }
        })
    }

    private fun showRecyclerGrid() {
        rv_Clubs.layoutManager = GridLayoutManager(this, 2)
        val adapterViewGrid = AdapterViewGrid(list)
        rv_Clubs.adapter = adapterViewGrid

        adapterViewGrid.setOnItemClickCallback(object : AdapterViewGrid.OnItemClickCallback {
            override fun onItemClicked(data: ClubDetails) {
                showSelectedSite(data)
            }
        })
    }

    private fun showRecyclerCardView() {
        rv_Clubs.layoutManager = LinearLayoutManager(this)
        val adapterViewCard = AdapterViewCard(this, list)
        rv_Clubs.adapter = adapterViewCard

    }

    private fun showSelectedSite(item: ClubDetails) {
        val data = Bundle()
        data.putString("name", item.name)
        data.putString("desc", item.desc)
        data.putInt("logo", item.logo)
        data.putString("stad_name", item.stad_name)
        data.putString("stad_desc", item.stad_desc)
        data.putString("stad_loc", item.stad_loc)
        data.putString("stad_geo", item.stad_geo)
        data.putInt("stad_img", item.stad_img)
        data.putBoolean("stat_pre", item.stat_pre)

        val moveWithDataIntent = Intent(this@MainActivity, ItemDetails::class.java)
        /*
        moveWithDataIntent.putExtra(ItemDetails.EXTRA_NAME, item.name)
        moveWithDataIntent.putExtra(ItemDetails.EXTRA_DESC, item.desc)
        moveWithDataIntent.putExtra(ItemDetails.EXTRA_LOC, item.loc)
        */
        moveWithDataIntent.putExtra(ItemDetails.EXTRA_ID, item.id)
        moveWithDataIntent.putExtra(ItemDetails.EXTRA_BOOL, item.stat_pre)
        moveWithDataIntent.putExtras(data)
        startActivity(moveWithDataIntent)
    }
}