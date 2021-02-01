package com.dnc.marketingapp.data.repositories

import com.dnc.core.marketingapp.model.TargetingSpecifics
import com.dnc.core.marketingapp.model.Targets
import com.dnc.core.marketingapp.repositories.MarketingsCampaignsRepository
import com.dnc.marketingapp.data.apiservice.BackendApiService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarketingCampaignsRepositoryImpl @Inject constructor(
    private val backendApiService: BackendApiService
): MarketingsCampaignsRepository {

    override var currentTargetingSpecificsList: ArrayList<TargetingSpecifics>? = null

    override fun getTargetingSpecifics(): Single<Targets>
        = backendApiService.getTargetingSpecificsData()
            .map {
                currentTargetingSpecificsList = it.targetingSpecifics
                it
            }

}