package com.egiwon.myrecyclerviewsample.di

import com.egiwon.myrecyclerviewsample.data.ImageRepository
import com.egiwon.myrecyclerviewsample.data.ImageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindImageRepository(repository: ImageRepositoryImpl): ImageRepository
}
