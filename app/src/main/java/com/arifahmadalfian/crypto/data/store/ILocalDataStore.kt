package com.arifahmadalfian.crypto.data.store

import com.arifahmadalfian.crypto.data.store.model.UserSettings

interface ILocalDataStore {
    suspend fun setDataStoreUser(username: String, password: String)
    suspend fun getDataStoreUser(): UserSettings
}