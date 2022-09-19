package com.arifahmadalfian.crypto.data.crp

import java.io.InputStream
import java.io.OutputStream

interface ICryptoManager {
    fun encrypt(bytes: ByteArray, outputStream: OutputStream): ByteArray
    fun decrypt(inputStream: InputStream): ByteArray
}