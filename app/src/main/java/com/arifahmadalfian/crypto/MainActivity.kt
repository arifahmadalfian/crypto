package com.arifahmadalfian.crypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arifahmadalfian.crypto.ui.theme.EncriptionAndDecryptionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cryptoManager = CryptoManager
        setContent {
            EncriptionAndDecryptionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var messageToEncrypt by remember {
                        mutableStateOf("")
                    }

                    var messageToDecrypt by remember {
                        mutableStateOf("")
                    }

                    var file by remember {
                        mutableStateOf(filesDir)
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp)
                    ) {
                        BasicTextField(
                            value = messageToEncrypt,
                            onValueChange = {
                                messageToEncrypt = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Button(
                                onClick = {
                                    messageToDecrypt = cryptoManager.toEncrypt(messageToEncrypt, file)
                                }
                            ) {
                                Text(text = "Encrypt")
                            }

                            Button(
                                onClick = {
                                    messageToDecrypt = cryptoManager.toDecrypt(file)
                                }
                            ) {
                                Text(text = "Decrypt")
                            }
                        }
                        Text(text = messageToDecrypt)
                    }
                }
            }
        }
    }
}
