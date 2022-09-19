package com.arifahmadalfian.crypto.data.crp

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject

class CryptoManagerFile @Inject constructor(
    private val cryptoManagerDatastore: ICryptoManagerDatastore
): ICryptoManagerFile {
    override fun encryptFile(str: String, filesDir: File, fileName: String): String {
        val bytes = str.encodeToByteArray()
        val file = File(filesDir, fileName)
        if (!file.exists()) {
            file.createNewFile()
        }
        val fos = FileOutputStream(file)

        return try {
            cryptoManagerDatastore.encryptDatastore(
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
            cryptoManagerDatastore.decryptDatastore(
                inputStream = FileInputStream(file)
            ).decodeToString()
        } catch (e: Exception) {
            "File Empty"
        }
    }
}