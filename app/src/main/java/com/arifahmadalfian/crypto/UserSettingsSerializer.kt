package com.arifahmadalfian.crypto

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class UserSettingsSerializer @Inject constructor(
    private val cryptoManager: ICryptoManagerFile
): Serializer<UserSettings> {

    override val defaultValue: UserSettings
        get() = UserSettings()

    override suspend fun readFrom(input: InputStream): UserSettings {
        TODO("Not yet implemented")
    }

    override suspend fun writeTo(t: UserSettings, output: OutputStream) {
        TODO("Not yet implemented")
    }
}