package com.dnc.marketingapp.presentation.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.dnc.marketingapp.R
import com.dnc.marketingapp.presentation.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.targeting_specifics_fragment)
    }

    override fun onSupportNavigateUp(): Boolean
            = NavigationUI.navigateUp(navController, null)

    override fun onBackPressed() {
        when(navController.currentDestination?.id){
            R.id.targetingSpecifics -> {
                finishAffinity()
            }else -> {
                super.onBackPressed()
            }
        }
    }
}