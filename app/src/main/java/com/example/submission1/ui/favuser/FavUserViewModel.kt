package com.example.submission1.ui.favuser

import UserRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submission1.App
import com.example.submission1.data.entity.FavUser

class FavUserViewModel(application: Application) : AndroidViewModel(application) {
    val userRepository: UserRepository = (application as App).userRepository
    fun getAllUser() : LiveData<List<FavUser>> = userRepository.getAllUser()
}