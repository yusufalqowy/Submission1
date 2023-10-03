package com.example.submission1.ui.favuser

import UserRepository
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submission1.data.entity.FavUser

class FavUserViewModel(application: Application) : ViewModel() {
    private val userRepository: UserRepository = UserRepository(application)

    fun getAllUser() : LiveData<List<FavUser>> = userRepository.getAllUser()
}