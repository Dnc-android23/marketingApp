package com.dnc.marketingapp.data.di.modules

import com.dnc.marketingapp.presentation.ui.campaigns.CampaignsDetailsFragment
import com.dnc.marketingapp.presentation.ui.channels.ChannelsFragment
import com.dnc.marketingapp.presentation.ui.main.MainActivity
import com.dnc.marketingapp.presentation.ui.reviewselection.ReviewCampaignFragment
import com.dnc.marketingapp.presentation.ui.splash.SplashActivity
import com.dnc.marketingapp.presentation.ui.targetingspecifics.TargetingSpecificsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class ViewBindingModule {
    @ContributesAndroidInjector
    abstract fun bindSplashScreenActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindMainScreenActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindChannelsFragment(): ChannelsFragment

    @ContributesAndroidInjector
    abstract fun bindCampaignDetailsFragment(): CampaignsDetailsFragment

    @ContributesAndroidInjector
    abstract fun bindTargetingSpecificsFragment(): TargetingSpecificsFragment

    @ContributesAndroidInjector
    abstract fun bindReviewCampaignFragment(): ReviewCampaignFragment

}