package com.semenovdev.vkfeed.ui

sealed class AuthState {
    object Authorized: AuthState()
    object Unauthorized: AuthState()
    object Initial: AuthState()
}