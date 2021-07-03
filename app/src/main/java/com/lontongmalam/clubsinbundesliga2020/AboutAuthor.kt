package com.lontongmalam.clubsinbundesliga2020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager

class AboutAuthor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_author)
        window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        var actionBar = getSupportActionBar()

        if (actionBar != null) {

            // Customize the back button
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_selector)

            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        supportActionBar?.title = "About"
    }
}