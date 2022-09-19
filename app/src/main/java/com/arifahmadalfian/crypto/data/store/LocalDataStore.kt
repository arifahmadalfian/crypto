package com.arifahmadalfian.crypto.data.store

import android.content.Context
import androidx.datastore.dataStore
import com.arifahmadalfian.crypto.data.crp.ICryptoManagerDatastore
import com.arifahmadalfian.crypto.data.store.model.UserSettings
import com.arifahmadalfian.crypto.data.store.serializer.UserSettingsSerializer
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LocalDataStore @Inject constructor(
    private val context: Context,
    cryptoManagerDatastore: ICryptoManagerDatastore
) : ILocalDataStore {

    private val Context.dataStoreUser by dataStore(
        fileName = "user-setting.json",
        serializer = UserSettingsSerializer(cryptoManagerDatastore)
    )

    override suspend fun setDataStoreUser(username: String, password: String) {
        context.dataStoreUser.updateData {
            it.copy(
                username = username,
                password = password
            )
        }
    }

    override suspend fun getDataStoreUser(): UserSettings {
        return context.dataStoreUser.data.first()
    }

}
