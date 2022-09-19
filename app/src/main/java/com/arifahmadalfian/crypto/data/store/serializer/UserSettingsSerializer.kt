package com.arifahmadalfian.crypto.data.store.serializer

import androidx.datastore.core.Serializer
import com.arifahmadalfian.crypto.data.crp.ICryptoManagerDatastore
import com.arifahmadalfian.crypto.data.store.model.UserSettings
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class UserSettingsSerializer(
    private val cryptoManager: ICryptoManagerDatastore
): Serializer<UserSettings> {

    override val defaultValue: UserSettings
        get() = UserSettings()

    override suspend fun readFrom(input: InputStream): UserSettings {
        val decryptBytes = cryptoManager.decryptDatastore(input)
        return try {
            Json.decodeFromString(
                deserializer = UserSettings.serializer(),
                string = decryptBytes.decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: UserSettings, output: OutputStream) {
        cryptoManager.encryptDatastore(
            bytes = Json.encodeToString(
                serializer = UserSettings.serializer(),
                value = t
            ).encodeToByteArray(),
            outputStream = output
        )
    }
}