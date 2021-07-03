package com.lontongmalam.clubsinbundesliga2020

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed(Runnable() {
            run(){
                val move = Intent(this, MainActivity::class.java)
                startActivity(move)
                finish()
            }
        }, 2000)
    }
}