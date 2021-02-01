package com.dnc.marketingapp.presentation.ui.base

import android.os.Bundle
import com.dnc.marketingapp.data.networkManager.NetworkManager
import com.dnc.marketingapp.data.receivers.ConnectionLiveData
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var networkManager: NetworkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ConnectionLiveData(this).observe(this, { isConnected ->
            if(networkManager.hasNetworkConnection != isConnected){
                networkManager.hasNetworkConnection = isConnected
            }
        })
    }
}