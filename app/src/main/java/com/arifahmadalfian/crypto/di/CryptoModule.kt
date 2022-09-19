package com.arifahmadalfian.crypto.di

import com.arifahmadalfian.crypto.data.crp.*
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

    @Provides
    @Singleton
    fun provideCryptoManagerDatastore(
        cryptoManager: ICryptoManager
    ): ICryptoManagerDatastore {
        return CryptoManagerDatastore(cryptoManager = cryptoManager)
    }

    @Provides
    @Singleton
    fun provideCryptoManagerFile(
        cryptoManagerDatastore: ICryptoManagerDatastore
    ): ICryptoManagerFile {
        return CryptoManagerFile(cryptoManagerDatastore = cryptoManagerDatastore)
    }
}