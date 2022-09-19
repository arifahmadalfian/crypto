package com.arifahmadalfian.crypto.data.crp

import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class CryptoManagerDatastore @Inject constructor(
    private val cryptoManager: ICryptoManager
) : ICryptoManagerDatastore {
    override fun encryptDatastore(bytes: ByteArray, outputStream: OutputStream): ByteArray {
        return cryptoManager.encrypt(bytes = bytes, outputStream = outputStream)
    }

    override fun decryptDatastore(inputStream: InputStream): ByteArray {
        return cryptoManager.decrypt(inputStream = inputStream)
    }
}