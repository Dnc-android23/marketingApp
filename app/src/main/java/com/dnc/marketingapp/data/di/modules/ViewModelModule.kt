package com.dnc.marketingapp.data.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dnc.marketingapp.data.di.utils.ViewModelFactory
import com.dnc.marketingapp.data.di.utils.ViewModelKey
import com.dnc.marketingapp.presentation.ui.campaigns.CampaignDetailsViewModel
import com.dnc.marketingapp.presentation.ui.channels.ChannelsViewModel
import com.dnc.marketingapp.presentation.ui.reviewselection.ReviewCampaignViewModel
import com.dnc.marketingapp.presentation.ui.targetingspecifics.TargetingSpecificsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TargetingSpecificsViewModel::class)
    abstract fun bindTargetingSpecificsViewModel(viewModel: TargetingSpecificsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChannelsViewModel::class)
    abstract fun bindChannelsViewModel(viewModel: ChannelsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CampaignDetailsViewModel::class)
    abstract fun bindCampaignDetailsViewModel(viewModel: CampaignDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReviewCampaignViewModel::class)
    abstract fun bindReviewCampaignViewModel(viewModel: ReviewCampaignViewModel): ViewModel

}