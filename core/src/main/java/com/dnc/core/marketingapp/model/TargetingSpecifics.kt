package com.dnc.core.marketingapp.model

import com.google.gson.annotations.SerializedName

data class TargetingSpecifics(
    @SerializedName("type")
    val name: String,
    @SerializedName("value")
    val channelList: ArrayList<String>
)