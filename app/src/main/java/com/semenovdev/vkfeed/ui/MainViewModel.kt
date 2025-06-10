package com.semenovdev.vkfeed.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult

class MainViewModel: ViewModel() {
    private val _authState = MutableLiveData<AuthState>(AuthState.Initial)
    val authState: LiveData<AuthState> = _authState

    init {
        _authState.value = if (VK.isLoggedIn()) {
            AuthState.Authorized
        } else {
            AuthState.Unauthorized
        }
    }

    fun performAuthResult(result: VKAuthenticationResult) {
        when(result) {
            is VKAuthenticationResult.Success -> {
                _authState.value = AuthState.Authorized
            }
            is VKAuthenticationResult.Failed -> {
                Log.d("MainViewModel", result.exception.toString())
                _authState.value = AuthState.Unauthorized
            }
        }
    }
}