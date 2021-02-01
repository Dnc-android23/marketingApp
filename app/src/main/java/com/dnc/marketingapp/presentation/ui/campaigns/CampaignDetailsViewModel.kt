package com.dnc.marketingapp.presentation.ui.campaigns

import androidx.lifecycle.MutableLiveData
import com.dnc.core.marketingapp.model.Campaign
import com.dnc.core.marketingapp.usecases.GetCampaignsDetailsUseCase
import com.dnc.marketingapp.data.networkManager.NetworkManager
import com.dnc.marketingapp.data.rx.SchedulersProvider
import com.dnc.marketingapp.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class CampaignDetailsViewModel @Inject constructor(
    private val schedulers: SchedulersProvider,
    private val campaignsDetailsUseCase: GetCampaignsDetailsUseCase,
    networkManager: NetworkManager
): BaseViewModel(networkManager){
    val campaignDetailsLiveData = MutableLiveData<List<Campaign>>()
    val serverErrorLiveData = MutableLiveData<String>()

    fun fetchCampaignDetails(channelName: String){
        compositeDisposable.add(
            campaignsDetailsUseCase.execute(channelName)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    campaignDetailsLiveData.postValue(it)
                }, {
                    serverErrorLiveData.postValue(it.message)
                })
        )
    }
}