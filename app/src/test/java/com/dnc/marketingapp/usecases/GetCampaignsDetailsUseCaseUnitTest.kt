package com.dnc.marketingapp.usecases

import com.dnc.core.marketingapp.repositories.ChannelDetailsRepository
import com.dnc.core.marketingapp.usecases.GetCampaignsDetailsUseCase
import com.dnc.marketingapp.utils.getChannelCampaigns
import com.dnc.marketingapp.utils.getEmptyChannelCampaigns
import com.dnc.marketingapp.utils.mock
import com.dnc.marketingapp.utils.whenever
import io.reactivex.Single
import org.junit.Test

class GetCampaignsDetailsUseCaseUnitTest {
    private val channelDetailsRepository: ChannelDetailsRepository = mock<ChannelDetailsRepository>()
    private val getCampaignsDetailsUseCase by lazy { GetCampaignsDetailsUseCase(channelDetailsRepository)  }

    @Test
    fun `testGetCampaignsDetailsUseCase execute completed`(){
        val currentChannelName = "ChannelB"

        whenever(channelDetailsRepository.getChannelCampaignsDetails(currentChannelName))
            .thenReturn(Single.just(getEmptyChannelCampaigns()))

        getCampaignsDetailsUseCase.execute(currentChannelName)
            .test()
            .assertComplete()
    }

    @Test
    fun `testGetCampaignsDetailsUseCase cache exists`(){
        val cachedCampaignDetails = getChannelCampaigns()
        val cachedChannelName = ""

        whenever(channelDetailsRepository.currentCampaignsDetails)
            .thenReturn(cachedCampaignDetails)

        whenever(channelDetailsRepository.currentChannel)
            .thenReturn(cachedChannelName)

        getCampaignsDetailsUseCase.execute(cachedChannelName)
            .test()
            .assertResult(cachedCampaignDetails.campaignsList)
    }

    @Test
    fun `testGetCampaignsDetailsUseCase cache exists for different channel`(){
        val cachedCampaignDetails = getChannelCampaigns()
        val responseFromServer = getEmptyChannelCampaigns()
        val cachedChannelName = "ChannelA"
        val currentChannelName = "ChannelB"

        whenever(channelDetailsRepository.currentCampaignsDetails)
            .thenReturn(cachedCampaignDetails)

        whenever(channelDetailsRepository.currentChannel)
            .thenReturn(cachedChannelName)

        whenever(channelDetailsRepository.getChannelCampaignsDetails(currentChannelName))
            .thenReturn(Single.just(responseFromServer))

        getCampaignsDetailsUseCase.execute(currentChannelName)
            .test()
            .assertResult(responseFromServer.campaignsList)
    }

    @Test
    fun `testGetCampaignsDetailsUseCase cache null for current channel`(){
        val cachedCampaignDetails = null
        val responseFromServer = getEmptyChannelCampaigns()
        val cachedChannelName = "ChannelA"

        whenever(channelDetailsRepository.currentCampaignsDetails)
            .thenReturn(cachedCampaignDetails)

        whenever(channelDetailsRepository.currentChannel)
            .thenReturn(cachedChannelName)

        whenever(channelDetailsRepository.getChannelCampaignsDetails(cachedChannelName))
            .thenReturn(Single.just(responseFromServer))

        getCampaignsDetailsUseCase.execute(cachedChannelName)
            .test()
            .assertResult(responseFromServer.campaignsList)
    }

    @Test
    fun `testGetCampaignsDetailsUseCase cache null`(){
        val responseFromServer = getChannelCampaigns()
        val currentChannelName = "ChannelA"

        whenever(channelDetailsRepository.currentCampaignsDetails)
            .thenReturn(null)

        whenever(channelDetailsRepository.getChannelCampaignsDetails(currentChannelName))
            .thenReturn(Single.just(responseFromServer))

        getCampaignsDetailsUseCase.execute(currentChannelName)
            .test()
            .assertResult(responseFromServer.campaignsList)
    }
}