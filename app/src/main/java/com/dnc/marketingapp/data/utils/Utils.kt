package com.dnc.marketingapp.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun isNetworkAvailable(context: Context): Boolean {
    (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)?.apply {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            activeNetworkInfo?.let { networkInfo ->
                return networkInfo.isConnected &&
                        (networkInfo.type == ConnectivityManager.TYPE_WIFI ||
                                networkInfo.type == ConnectivityManager.TYPE_MOBILE)
            }
        } else {
            activeNetwork?.let { activeNetwork ->
                return getNetworkCapabilities(activeNetwork)?.let {networkCapabilities ->
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)

                } ?: false
            }
        }
    }
    return false
}