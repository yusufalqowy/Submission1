package com.example.submission1

import UserRepository
import android.app.Application
import com.example.submission1.database.UserDatabase

class App : Application() {
    val db by lazy { UserDatabase.getDatabase(this) }
    val userRepository by lazy { UserRepository(db.userGithubDao()) }
}