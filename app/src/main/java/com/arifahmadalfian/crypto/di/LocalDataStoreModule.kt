package com.arifahmadalfian.crypto.di

import android.content.Context
import com.arifahmadalfian.crypto.data.crp.CryptoManagerDatastore
import com.arifahmadalfian.crypto.data.crp.ICryptoManagerDatastore
import com.arifahmadalfian.crypto.data.store.ILocalDataStore
import com.arifahmadalfian.crypto.data.store.LocalDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalDataStoreModule {

    @Provides
    @Singleton
    fun provideLocalDataStore(
        @ApplicationContext context: Context,
        cryptoManagerDatastore: ICryptoManagerDatastore
    ): ILocalDataStore {
        return LocalDataStore(
            context = context,
            cryptoManagerDatastore = cryptoManagerDatastore
        )
    }
}