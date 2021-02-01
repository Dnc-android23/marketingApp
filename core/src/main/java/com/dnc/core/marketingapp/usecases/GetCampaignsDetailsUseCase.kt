package com.dnc.core.marketingapp.usecases

import com.dnc.core.marketingapp.model.Campaign
import com.dnc.core.marketingapp.repositories.ChannelDetailsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCampaignsDetailsUseCase @Inject constructor(
    private val channelDetailsRepository: ChannelDetailsRepository
) {

    fun execute(channelName: String): Single<List<Campaign>> {
        return if (channelName == channelDetailsRepository.currentChannel) {
            channelDetailsRepository.currentCampaignsDetails?.let {
                Single.just(it.campaignsList)
            } ?: run {
                channelDetailsRepository.getChannelCampaignsDetails(channelName)
                    .map { it.campaignsList }
            }
        } else {
            channelDetailsRepository.getChannelCampaignsDetails(channelName)
                .map {
                    it.campaignsList
                }

        }
    }

}