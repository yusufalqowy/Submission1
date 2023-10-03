package com.example.submission1.ui.darktheme

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.submission1.ui.darktheme.ThemeSettingPreferences
import kotlinx.coroutines.launch

class ThemeViewModel(private val pref: ThemeSettingPreferences): ViewModel() {
    fun getThemeSetting(): LiveData<Boolean>{
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean){
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}