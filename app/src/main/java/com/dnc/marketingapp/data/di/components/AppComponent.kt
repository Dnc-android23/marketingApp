package com.dnc.marketingapp.data.di.components

import android.app.Application
import com.dnc.marketingapp.data.di.modules.ViewBindingModule
import com.dnc.marketingapp.data.di.modules.ApiModule
import com.dnc.marketingapp.data.di.modules.AppModule
import com.dnc.marketingapp.data.di.modules.NetworkModule
import com.dnc.marketingapp.presentation.MarketingApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewBindingModule::class,
        ApiModule::class,
        AppModule::class,
        NetworkModule::class,
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<MarketingApplication>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder

        fun build(): AppComponent
    }
}