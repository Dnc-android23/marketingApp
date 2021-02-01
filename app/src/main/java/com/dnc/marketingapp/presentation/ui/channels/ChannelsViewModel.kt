package com.dnc.marketingapp.presentation.ui.channels

import androidx.lifecycle.MutableLiveData
import com.dnc.core.marketingapp.usecases.FilterChannelsUseCase
import com.dnc.marketingapp.data.networkManager.NetworkManager
import com.dnc.marketingapp.data.rx.SchedulersProvider
import com.dnc.marketingapp.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class ChannelsViewModel @Inject constructor(
    private val schedulers: SchedulersProvider,
    private val filterChannelsUseCase: FilterChannelsUseCase,
    networkManager: NetworkManager
) : BaseViewModel(networkManager){

    val channelsLiveData = MutableLiveData<List<String>>()

    fun fetchFilteredChannels(filters: ArrayList<String>){
        compositeDisposable.add(
            filterChannelsUseCase.execute(filters)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe{it ->
                    channelsLiveData.postValue(it)
                }
        )
    }
}