package com.arifahmadalfian.crypto.di

import com.arifahmadalfian.crypto.CryptoManagerFile
import com.arifahmadalfian.crypto.ICryptoManagerFile
import com.arifahmadalfian.crypto.ICryptoMangerDatastore
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
    fun provideCryptoManagerFile(): ICryptoManagerFile {
        return CryptoManagerFile()
    }

    @Provides
    @Singleton
    fun provideCryptoManagerDatastore(): ICryptoMangerDatastore {
        return CryptoManagerFile()
    }
}