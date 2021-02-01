package com.dnc.marketingapp.usecases

import com.dnc.core.marketingapp.model.Targets
import com.dnc.core.marketingapp.repositories.MarketingsCampaignsRepository
import com.dnc.core.marketingapp.usecases.GetTargetingSpecificsInfoUseCase
import com.dnc.marketingapp.utils.mock
import com.dnc.marketingapp.utils.targetingSpecificsModel
import com.dnc.marketingapp.utils.whenever
import io.reactivex.Single
import org.junit.Test

class GetTargetingSpecificsInfoUseCaseUnitTest {
    private val marketingCampaignsRepository = mock<MarketingsCampaignsRepository>()
    private val getTargetingSpecificsInfoUseCase by lazy { GetTargetingSpecificsInfoUseCase(marketingCampaignsRepository)  }

    @Test
    fun `testGetTargetingSpecificsInfoUseCase execute completed`(){
        whenever(marketingCampaignsRepository.getTargetingSpecifics())
            .thenReturn(Single.just(Targets(arrayListOf())))

        getTargetingSpecificsInfoUseCase.execute()
            .test()
            .assertComplete()
    }

    @Test
    fun `testGetTargetingSpecificsInfoUseCase cache exists`(){
        val cachedResponse = targetingSpecificsModel()
        whenever(marketingCampaignsRepository.currentTargetingSpecificsList)
            .thenReturn(cachedResponse)

        getTargetingSpecificsInfoUseCase.execute()
            .test()
            .assertResult(cachedResponse.map { it.name })
    }

    @Test
    fun `testGetTargetingSpecificsInfoUseCase cache null`(){
        val cachedResponse = null
        val responseFromServer = Targets(arrayListOf())
        whenever(marketingCampaignsRepository.currentTargetingSpecificsList)
            .thenReturn(cachedResponse)
        whenever(marketingCampaignsRepository.getTargetingSpecifics())
            .thenReturn(Single.just(responseFromServer))

        getTargetingSpecificsInfoUseCase.execute()
            .test()
            .assertResult(responseFromServer.targetingSpecifics.map { it.name })
    }
}