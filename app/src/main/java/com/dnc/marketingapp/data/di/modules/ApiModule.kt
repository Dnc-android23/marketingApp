package com.dnc.marketingapp.data.di.modules

import com.dnc.marketingapp.data.apiservice.BackendApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class ApiModule {
    @Provides
    fun bindApiService(retrofit: Retrofit): BackendApiService {
        return retrofit.create(BackendApiService::class.java)
    }
}