package com.dnc.marketingapp.presentation.ui.base

import androidx.lifecycle.ViewModel
import com.dnc.marketingapp.data.networkManager.NetworkManager
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(val networkManager: NetworkManager)
    : ViewModel(){

    protected val compositeDisposable = CompositeDisposable()

    val hasInternetConnection: Boolean
        get() = networkManager.hasNetworkConnection

    fun clearSession(): Unit = compositeDisposable.clear()

    override fun onCleared() {
        compositeDisposable.clear()
    }
}