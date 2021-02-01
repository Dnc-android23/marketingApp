package com.dnc.marketingapp.utils

import com.dnc.core.marketingapp.model.Campaign
import com.dnc.core.marketingapp.model.ChannelCampaigns
import com.dnc.core.marketingapp.model.TargetingSpecifics

fun targetingSpecificsModel() =
    arrayListOf(TargetingSpecifics(
        name ="Location",
        channelList = arrayListOf("Facebook", "Instagram", "Twitter")),
        TargetingSpecifics(
            name ="Sex",
            channelList = arrayListOf("Facebook", "SEO", "Linkedin")))

fun channelListLocation() = arrayListOf("Facebook", "Instagram", "Twitter")

fun targetingSpecificsListLocation() = arrayListOf("Location", "Sex", "Education", "Expertise")

fun getChannelCampaigns() =
    ChannelCampaigns(arrayListOf(
        Campaign(123, "EUR", 0, null, 1,
        arrayListOf("Scope setup","Market research", "Keywords research", "Content analysis", "Local SEO", "Email support"))
    ))

fun getEmptyChannelCampaigns() = ChannelCampaigns(arrayListOf())