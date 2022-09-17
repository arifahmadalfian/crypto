package com.arifahmadalfian.crypto

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.io.*
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import kotlin.Exception

class CryptoManagerFile: ICryptoManagerFile, ICryptoMangerDatastore {

    companion object {
        private const val ANDROID_KEY_STORE = "AndroidKeyStore"
        private const val SECRET = "secret"

        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    }

    private val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE).apply {
        load(null)
    }

    private val encryptCipher = Cipher.getInstance(TRANSFORMATION).apply {
        init(Cipher.ENCRYPT_MODE, getKey())
    }

    private fun getDecryptCipherForIv(iv: ByteArray): Cipher {
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
        }
    }

    private fun getKey(): SecretKey {
        val existingKey = keyStore.getEntry(SECRET, null) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey {
        return KeyGenerator.getInstance(ALGORITHM).apply {
            init(
                KeyGenParameterSpec.Builder(
                    SECRET,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setUserAuthenticationRequired(false)
                    .setRandomizedEncryptionRequired(true)
                    .build()
            )
        }.generateKey()
    }

    override fun encryptDatastore(bytes: ByteArray, outputStream: OutputStream): ByteArray {
        val encryptedBytes = encryptCipher.doFinal(bytes)
        outputStream.use {
            it.write(encryptCipher.iv.size)
            it.write(encryptCipher.iv)
            it.write(encryptedBytes.size)
            it.write(encryptedBytes)
        }
        return encryptedBytes
    }

    override fun decryptDatastore(inputStream: InputStream): ByteArray {
        return inputStream.use {
            val ivSize = it.read()
            val iv = ByteArray(ivSize)
            it.read(iv)

            val encryptedBytesSize = it.read()
            val encryptedBytes = ByteArray(encryptedBytesSize)
            it.read(encryptedBytes)

            getDecryptCipherForIv(iv).doFinal(encryptedBytes)
        }
    }

    override fun encryptFile(str: String, filesDir: File, fileName: String): String {
        val bytes = str.encodeToByteArray()
        val file = File(filesDir, fileName)
        if (!file.exists()) {
            file.createNewFile()
        }
        val fos = FileOutputStream(file)

        return try {
            encryptDatastore(
                bytes = bytes,
                outputStream = fos
            ).decodeToString()
        } catch (e: Exception) {
            "$e"
        }

    }

    override fun decryptFile(filesDir: File, fileName: String): String {
        val file = File(filesDir, fileName)
        return try {
            decryptDatastore(
                inputStream = FileInputStream(file)
            ).decodeToString()
        } catch (e: Exception) {
            "File Empty"
        }

    }
}

interface ICryptoManagerFile {
    fun encryptFile(str: String, filesDir: File, fileName: String = "secret.txt"): String
    fun decryptFile(filesDir: File, fileName: String = "secret.txt"): String
}

interface ICryptoMangerDatastore {
    fun encryptDatastore(bytes: ByteArray, outputStream: OutputStream): ByteArray
    fun decryptDatastore(inputStream: InputStream): ByteArray
}