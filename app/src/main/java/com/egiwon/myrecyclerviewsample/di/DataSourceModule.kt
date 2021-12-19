package com.egiwon.myrecyclerviewsample.di

import com.egiwon.myrecyclerviewsample.data.ImageDataSource
import com.egiwon.myrecyclerviewsample.data.source.ImageDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindImageDataSource(imageDataSource: ImageDataSourceImpl): ImageDataSource
}
