package com.example.submission1.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewThemeModelFactory(private val pref: ThemeSettingPreferences) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHEKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThemeViewModel::class.java)){
            return ThemeViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class:" + modelClass)
    }
}