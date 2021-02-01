package com.dnc.core.marketingapp.model

import com.google.gson.annotations.SerializedName

data class ChannelCampaigns(
    @SerializedName("channelCampaigns")
    val campaignsList: ArrayList<Campaign>? = null
)