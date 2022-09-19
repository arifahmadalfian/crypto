package com.arifahmadalfian.crypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arifahmadalfian.crypto.data.crp.ICryptoManagerFile
import com.arifahmadalfian.crypto.ui.theme.EncriptionAndDecryptionTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var cryptoManager: ICryptoManagerFile

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

                    var username by remember {
                        mutableStateOf("")
                    }

                    var password by remember {
                        mutableStateOf("")
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Button(
                                onClick = {
                                    messageToDecrypt = cryptoManager.encryptFile(messageToEncrypt, filesDir)
                                }
                            ) {
                                Text(text = "Encrypt")
                            }

                            Button(
                                onClick = {
                                    messageToDecrypt = cryptoManager.decryptFile(filesDir)
                                }
                            ) {
                                Text(text = "Decrypt")
                            }
                        }
                        Text(text = messageToDecrypt)
                        Spacer(modifier = Modifier.height(16.dp))
                        BasicTextField(
                            value = username,
                            onValueChange = {
                                username = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        BasicTextField(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Button(
                                onClick = {
                                    viewModel.setDataStoreUser(
                                        username = username,
                                        password = password
                                    )
                                }
                            ) {
                                Text(text = "save")
                            }

                            Button(
                                onClick = viewModel::getDataStoreUser
                            ) {
                                Text(text = "load")
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = viewModel.users.value.toString())
                    }
                }
            }
        }
    }
}

