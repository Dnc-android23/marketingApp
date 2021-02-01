package com.dnc.marketingapp.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import com.dnc.marketingapp.R
import com.dnc.marketingapp.presentation.ui.base.BaseActivity
import com.dnc.marketingapp.presentation.ui.main.MainActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Timer().schedule(1000) {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }
    }
}