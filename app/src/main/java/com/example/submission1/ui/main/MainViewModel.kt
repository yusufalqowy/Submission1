package com.example.submission1.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission1.data.response.User
import com.example.submission1.data.response.UserResponse
import com.example.submission1.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    companion object{
        private const val TAG ="MainViewModel"
    }
    private val _listUsers = MutableLiveData<ArrayList<User>>()
    val listUsers : LiveData<ArrayList<User>> = _listUsers

    fun setSearchUsers(query: String){
        val client = ApiConfig.apiInstance.getSearchUsers(query)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful)
                    _listUsers.value = response.body()?.items
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e(TAG, "${t.message.toString()}")
            }
        })
    }
    fun getSearchUser(): LiveData<ArrayList<User>>{
        return listUsers
    }

}