package com.arifahmadalfian.crypto.data.crp

import java.io.File

interface ICryptoManagerFile {
    fun encryptFile(str: String, filesDir: File, fileName: String = "secret.txt"): String
    fun decryptFile(filesDir: File, fileName: String = "secret.txt"): String
}