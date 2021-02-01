package com.dnc.marketingapp.data.repositories

import com.dnc.core.marketingapp.model.ChannelCampaigns
import com.dnc.core.marketingapp.repositories.ChannelDetailsRepository
import com.dnc.marketingapp.data.apiservice.BackendApiService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChannelCampaignsDetailsRepositoryImpl @Inject constructor(
    private val backendApiService: BackendApiService
) : ChannelDetailsRepository {

    override var currentCampaignsDetails: ChannelCampaigns? = null
    override var currentChannel: String = ""

    override fun getChannelCampaignsDetails(channelName: String): Single<ChannelCampaigns> {
        currentChannel = channelName
        return backendApiService.getChannelData(channelName).map {
            currentCampaignsDetails = it
            it
        }
    }

}