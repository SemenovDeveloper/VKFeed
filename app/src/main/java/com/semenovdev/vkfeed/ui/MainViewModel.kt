package com.semenovdev.vkfeed.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.id.AccessToken
import com.vk.id.VKID
import com.vk.id.VKIDAuthFail
import com.vk.id.auth.VKIDAuthCallback
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _authState = MutableLiveData<AuthState>(AuthState.Initial)
    val authState: LiveData<AuthState> = _authState

    init {
        _authState.value = if (VKID.instance.accessToken?.token?.isNotEmpty() == true) {
            AuthState.Authorized
        } else {
            AuthState.Unauthorized
        }
    }

    private val vkAuthCallback = object : VKIDAuthCallback {
        override fun onAuth(accessToken: AccessToken) {
            _authState.value = AuthState.Authorized
        }

        override fun onFail(fail: VKIDAuthFail) {
            _authState.value = AuthState.Unauthorized
        }
    }

    fun performAuth() {
        viewModelScope.launch {
            VKID.instance.authorize(vkAuthCallback)
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