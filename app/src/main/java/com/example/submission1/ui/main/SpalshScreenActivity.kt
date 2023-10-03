package com.example.submission1.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.submission1.R

class SpalshScreenActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh_screen)

        Handler().postDelayed({
            val mainIntent = Intent(this@SpalshScreenActivity, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, splashTimeOut)
    }
}