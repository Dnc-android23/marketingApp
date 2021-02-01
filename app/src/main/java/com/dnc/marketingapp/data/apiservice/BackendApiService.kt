package com.dnc.marketingapp.data.apiservice

import com.dnc.core.marketingapp.model.ChannelCampaigns
import com.dnc.core.marketingapp.model.Targets
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BackendApiService {
    @GET("/getTargetingSpecifics")
    fun getTargetingSpecificsData():  Single<Targets>

    @GET("/getChannelDetails")
    fun getChannelData(
        @Query("channelName") channelName: String
    ): Single<ChannelCampaigns>
}