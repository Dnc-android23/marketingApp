package com.dnc.core.marketingapp.usecases

import com.dnc.core.marketingapp.model.TargetingSpecifics
import com.dnc.core.marketingapp.repositories.MarketingsCampaignsRepository
import io.reactivex.Single
import javax.inject.Inject

class FilterChannelsUseCase @Inject constructor(
    private val marketingCampaignsRepository: MarketingsCampaignsRepository
) {
    fun execute(filters: ArrayList<String>): Single<List<String>> {
        return marketingCampaignsRepository.currentTargetingSpecificsList?.let {
            Single.just(getFilteredListOfChannels(filters, it))
        } ?: run {
            marketingCampaignsRepository.getTargetingSpecifics()
                .map { targets->
                    getFilteredListOfChannels(filters, targets.targetingSpecifics)
                }
        }
    }

    private fun getFilteredListOfChannels(
        filters: ArrayList<String>,
        targetingSpecificsList: ArrayList<TargetingSpecifics>): List<String> {

        return targetingSpecificsList
            .filter {
                filters.contains( it.name) }
            .flatMap {
                it.channelList }
            .distinct()
    }
}