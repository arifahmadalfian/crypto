package com.arifahmadalfian.crypto

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifahmadalfian.crypto.data.store.ILocalDataStore
import com.arifahmadalfian.crypto.data.store.model.UserSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val localDataStore: ILocalDataStore
) : ViewModel() {

    var users = mutableStateOf(UserSettings())
        private set

    fun setDataStoreUser(username: String, password: String) {
        viewModelScope.launch {
            localDataStore.setDataStoreUser(username, password)
        }
    }

    fun getDataStoreUser() {
        viewModelScope.launch {
            users.value = localDataStore.getDataStoreUser()
        }
    }
}
