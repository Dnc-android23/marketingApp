package com.dnc.marketingapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dnc.core.marketingapp.model.Targets
import com.dnc.core.marketingapp.repositories.MarketingsCampaignsRepository
import com.dnc.core.marketingapp.usecases.GetTargetingSpecificsInfoUseCase
import com.dnc.marketingapp.data.networkManager.NetworkManager
import com.dnc.marketingapp.data.rx.ImmediateSchedulerProvider
import com.dnc.marketingapp.presentation.ui.targetingspecifics.TargetingSpecificsViewModel
import com.dnc.marketingapp.utils.mock
import com.dnc.marketingapp.utils.targetingSpecificsModel
import com.dnc.marketingapp.utils.whenever
import io.reactivex.Single
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.verify


class TargetingSpecificsUnitTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val marketingCampaignsRepository = mock<MarketingsCampaignsRepository>()
    private val targetingSpecificsInfoUseCase by lazy { GetTargetingSpecificsInfoUseCase(
        marketingCampaignsRepository
    )  }

    private val schedulers = ImmediateSchedulerProvider()
    private val networkManager = mock<NetworkManager>()
    private val observerState = mock<Observer<List<String>>>()
    private val errorObserver = mock<Observer<String>>()

    private val targetingSpecificsViewModel by lazy { TargetingSpecificsViewModel(
        schedulers,
        targetingSpecificsInfoUseCase,
        networkManager
    ) }

    @Test
    fun `fetchTargetingSpecifics update list with elements`(){
        val response = Targets(targetingSpecificsModel())
        val argumentCaptor = ArgumentCaptor.forClass(List::class.java)

        whenever(marketingCampaignsRepository.getTargetingSpecifics())
            .thenReturn(Single.just(response))

        targetingSpecificsViewModel.targetingSpecificsLiveData.observeForever(observerState)

        targetingSpecificsViewModel.fetchTargetingSpecifics()

        verify(observerState).onChanged(argumentCaptor.capture() as List<String>?)
    }

    @Ignore("added for proof of concept only")
    @Test
    fun `fetchTargetingSpecifics throw error`(){
        val throwable = Throwable("Exception")

        whenever(targetingSpecificsInfoUseCase.execute()).thenReturn(Single.error(throwable))

        targetingSpecificsViewModel.targetingSpecificsLiveData.observeForever(observerState)
        targetingSpecificsViewModel.serverErrorLiveData.observeForever(errorObserver)

        targetingSpecificsViewModel.fetchTargetingSpecifics()

        verify(errorObserver).onChanged(throwable.message)
    }
}