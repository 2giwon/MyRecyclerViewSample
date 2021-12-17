package com.egiwon.myrecyclerviewsample.di

import com.egiwon.myrecyclerviewsample.data.RandomImageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkServiceModule {

    @Provides
    @Singleton
    fun provideRandomImageService(retrofit: Retrofit): RandomImageService =
        retrofit.create(RandomImageService::class.java)
}
