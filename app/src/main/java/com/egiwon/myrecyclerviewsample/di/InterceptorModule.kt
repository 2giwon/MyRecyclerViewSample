package com.egiwon.myrecyclerviewsample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InterceptorModule {

    @Provides
    @Singleton
    fun provideQueryInterceptor(): Interceptor {
        return Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("client_id", "fwys9Zgd8cBBdZmwjdOQSKtfpotT3vVZJNnLxYMBhvk")
                .build()

            val requestBuilder = chain.request().newBuilder().url(url)
            chain.proceed(requestBuilder.build())
        }
    }
}
