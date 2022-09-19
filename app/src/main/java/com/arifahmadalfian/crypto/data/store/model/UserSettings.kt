package com.arifahmadalfian.crypto.data.store.model

import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val username: String? = null,
    val password: String? = null
)
