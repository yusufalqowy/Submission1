package com.example.submission1.ui.detail

import UserRepository
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.submission1.App
import com.example.submission1.data.response.DetailUserResponse
import com.example.submission1.data.entity.FavUser
import com.example.submission1.data.response.User
import com.example.submission1.database.FavUserDao
import com.example.submission1.database.UserDatabase
import com.example.submission1.retrofit.ApiConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "DetailUserViewModel"
    }

    private val _UserDao = MutableLiveData<FavUserDao>()
    val userDao: LiveData<FavUserDao> = _UserDao

    private val _UserDb = MutableLiveData<UserDatabase>()
    val userDb: LiveData<UserDatabase> = _UserDb

    private val _user = MutableLiveData<DetailUserResponse>()
    val user: LiveData<DetailUserResponse> = _user

    private val userRepository: UserRepository = (application as App).userRepository
    fun getAllUser(): LiveData<List<FavUser>> = userRepository.getAllUser()
    fun insert(username: FavUser) {
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.insert(username)
        }

        Log.d(TAG,"Insert, $username")
        Log.d(TAG,"Insert, ${username.login}")
        Log.d(TAG,"Insert, ${username.avatarUrl}")
    }
    fun delete(username: FavUser) {
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.delete(username)
        }
    }


    fun setUserDetail(username: String) {
        val client = ApiConfig.apiInstance.getDetailUsers(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful)
                    _user.value = response.body()
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                Log.e(TAG, "${t.message.toString()}")
            }

        })

    }

    fun getUserDetail(): LiveData<DetailUserResponse> {
        return user
    }

}