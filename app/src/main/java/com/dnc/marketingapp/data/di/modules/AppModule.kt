package com.dnc.marketingapp.data.di.modules

import android.app.Application
import android.content.Context
import com.dnc.core.marketingapp.repositories.ChannelDetailsRepository
import com.dnc.core.marketingapp.repositories.MarketingsCampaignsRepository
import com.dnc.marketingapp.data.networkManager.NetworkManager
import com.dnc.marketingapp.data.networkManager.NetworkManagerImplementation
import com.dnc.marketingapp.data.repositories.ChannelCampaignsDetailsRepositoryImpl
import com.dnc.marketingapp.data.repositories.MarketingCampaignsRepositoryImpl
import com.dnc.marketingapp.data.rx.SchedulersFacade
import com.dnc.marketingapp.data.rx.SchedulersProvider
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: Application): Context

    @Binds
    abstract fun providerScheduler(schedulersFacade: SchedulersFacade): SchedulersProvider

    @Binds
    abstract fun provideNetworkManager(networkManagerImplementation: NetworkManagerImplementation): NetworkManager

    @Binds
    abstract fun provideMarketingCampaignsRepository(marketingCampaignsRepositoryImpl: MarketingCampaignsRepositoryImpl): MarketingsCampaignsRepository

    @Binds
    abstract fun provideChannelCampaignsDetailsRepository(channelCampaignsDetailsRepositoryImpl: ChannelCampaignsDetailsRepositoryImpl): ChannelDetailsRepository

}