package com.dnc.core.marketingapp.usecases

import com.dnc.core.marketingapp.model.TargetingSpecifics
import com.dnc.core.marketingapp.repositories.MarketingsCampaignsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetTargetingSpecificsInfoUseCase @Inject constructor(
    private val marketingCampaignsRepository: MarketingsCampaignsRepository
) {

    fun execute(): Single<List<String>> {
        return marketingCampaignsRepository.currentTargetingSpecificsList?.let {
            Single.just(getListOfTargetingSpecifics(it))
        } ?: run {
            marketingCampaignsRepository.getTargetingSpecifics()
                .map { targets->
                    getListOfTargetingSpecifics(targets.targetingSpecifics)
                }.onErrorReturn{emptyList()}
        }
    }

    private fun getListOfTargetingSpecifics(initialList : ArrayList<TargetingSpecifics>): List<String>
        = initialList.map { it.name }
}