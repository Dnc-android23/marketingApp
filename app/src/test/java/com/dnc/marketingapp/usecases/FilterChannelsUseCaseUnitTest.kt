package com.dnc.marketingapp.usecases

import com.dnc.core.marketingapp.model.TargetingSpecifics
import com.dnc.core.marketingapp.model.Targets
import com.dnc.core.marketingapp.repositories.MarketingsCampaignsRepository
import com.dnc.core.marketingapp.usecases.FilterChannelsUseCase
import com.dnc.marketingapp.utils.channelListLocation
import com.dnc.marketingapp.utils.mock
import com.dnc.marketingapp.utils.targetingSpecificsModel
import com.dnc.marketingapp.utils.whenever
import io.reactivex.Single
import org.junit.Test

class FilterChannelsUseCaseUnitTest {
    private val marketingCampaignsRepository = mock<MarketingsCampaignsRepository>()
    private val getFilterChannelsUseCase by lazy { FilterChannelsUseCase(marketingCampaignsRepository)  }

    @Test
    fun `testFilterChannelsUseCase execute completed`(){
        whenever(marketingCampaignsRepository.getTargetingSpecifics())
            .thenReturn(Single.just(Targets(arrayListOf())))

        getFilterChannelsUseCase.execute(arrayListOf())
            .test()
            .assertComplete()
    }

    @Test
    fun `testFilterChannelsUseCase cache exists`(){
        val cachedResponse = targetingSpecificsModel()
        val filters = arrayListOf<String>()
        whenever(marketingCampaignsRepository.currentTargetingSpecificsList)
            .thenReturn(cachedResponse)

        getFilterChannelsUseCase.execute(filters)
            .test()
            .assertResult(getFilteredListOfChannels(filters, cachedResponse))
    }

    @Test
    fun `testFilterChannelsUseCase cache null`(){
        val cachedResponse = null
        val responseFromServer = Targets(arrayListOf())
        val filters = arrayListOf<String>()

        whenever(marketingCampaignsRepository.currentTargetingSpecificsList)
            .thenReturn(cachedResponse)
        whenever(marketingCampaignsRepository.getTargetingSpecifics())
            .thenReturn(Single.just(responseFromServer))

        getFilterChannelsUseCase.execute(filters)
            .test()
            .assertResult(getFilteredListOfChannels(filters, responseFromServer.targetingSpecifics))
    }

    @Test
    fun `testFilterChannelsUseCase filters`(){
        val cachedResponse = targetingSpecificsModel()
        val filters = arrayListOf("Location")

        whenever(marketingCampaignsRepository.currentTargetingSpecificsList)
            .thenReturn(cachedResponse)

        val expectedResult = channelListLocation()
        getFilterChannelsUseCase.execute(filters)
            .test()
            .assertResult(expectedResult)
    }

    private fun getFilteredListOfChannels(
        filters: ArrayList<String>,
        targetingSpecificsList: ArrayList<TargetingSpecifics>): List<String> {

        return targetingSpecificsList
            .filter {
                filters.contains( it.name) }
            .flatMap {
                it.channelList }
            .distinct()
    }
}