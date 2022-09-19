package com.arifahmadalfian.crypto.data.crp

import java.io.InputStream
import java.io.OutputStream

interface ICryptoManagerDatastore {
    fun encryptDatastore(bytes: ByteArray, outputStream: OutputStream): ByteArray
    fun decryptDatastore(inputStream: InputStream): ByteArray
}