package com.dnc.marketingapp.presentation.ui.reviewselection

import com.dnc.marketingapp.data.networkManager.NetworkManager
import com.dnc.marketingapp.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class ReviewCampaignViewModel@Inject constructor(
    networkManager: NetworkManager
) : BaseViewModel(networkManager)