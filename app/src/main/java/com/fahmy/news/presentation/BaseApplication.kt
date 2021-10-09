package com.fahmy.news.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {

    private val isDark = mutableStateOf(false)

    fun toggleTheme() {
        this.isDark.value = !this.isDark.value
    }

    fun isDark(): Boolean = this.isDark.value
}