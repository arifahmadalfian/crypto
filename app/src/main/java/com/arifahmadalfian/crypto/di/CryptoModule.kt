package com.arifahmadalfian.crypto.di

import com.arifahmadalfian.crypto.CryptoManager
import com.arifahmadalfian.crypto.ICryptoManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CryptoModule {

    @Provides
    @Singleton
    fun provideCryptoManager(): ICryptoManager {
        return CryptoManager()
    }
}