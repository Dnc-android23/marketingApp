package com.dnc.marketingapp.presentation

import android.app.Activity
import com.dnc.marketingapp.data.di.components.AppComponent
import com.dnc.marketingapp.data.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MarketingApplication: DaggerApplication() , HasAndroidInjector{
    private lateinit var appComponent: AppComponent

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        return appComponent
    }
}