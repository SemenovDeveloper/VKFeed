package com.semenovdev.vkfeed.presentation.main

sealed class AuthState {
    object Authorized: AuthState()
    object Unauthorized: AuthState()
    object Initial: AuthState()
}