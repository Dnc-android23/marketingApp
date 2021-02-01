package com.dnc.marketingapp.presentation.ui.targetingspecifics

import androidx.lifecycle.MutableLiveData
import com.dnc.core.marketingapp.usecases.GetTargetingSpecificsInfoUseCase
import com.dnc.marketingapp.data.networkManager.NetworkManager
import com.dnc.marketingapp.data.rx.SchedulersProvider
import com.dnc.marketingapp.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class TargetingSpecificsViewModel @Inject constructor(
    private val schedulers: SchedulersProvider,
    private val targetingSpecificsInfoUseCase: GetTargetingSpecificsInfoUseCase,
    networkManager: NetworkManager
) : BaseViewModel(networkManager){

    val targetingSpecificsLiveData = MutableLiveData<List<String>>()
    val serverErrorLiveData = MutableLiveData<String>()

    fun fetchTargetingSpecifics(){
        compositeDisposable.add(
            targetingSpecificsInfoUseCase.execute()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe( {
                    targetingSpecificsLiveData.postValue(it)
                }, {
                    serverErrorLiveData.postValue(it.message)
                })
        )
    }
}