package com.dnc.core.marketingapp.repositories

import com.dnc.core.marketingapp.model.TargetingSpecifics
import com.dnc.core.marketingapp.model.Targets
import io.reactivex.Single

interface MarketingsCampaignsRepository {
    var currentTargetingSpecificsList: ArrayList<TargetingSpecifics>?
    fun getTargetingSpecifics(): Single<Targets>
}