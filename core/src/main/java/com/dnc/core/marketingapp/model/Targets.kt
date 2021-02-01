package com.dnc.core.marketingapp.model

import com.google.gson.annotations.SerializedName

data class Targets(
    @SerializedName("targetSpecifics")
    val targetingSpecifics: ArrayList<TargetingSpecifics>
)