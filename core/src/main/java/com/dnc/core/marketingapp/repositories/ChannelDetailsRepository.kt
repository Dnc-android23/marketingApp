package com.dnc.core.marketingapp.repositories

import com.dnc.core.marketingapp.model.ChannelCampaigns
import io.reactivex.Single

interface ChannelDetailsRepository {
    var currentCampaignsDetails : ChannelCampaigns?
    var currentChannel: String
    fun getChannelCampaignsDetails(channelName: String) : Single<ChannelCampaigns>
}