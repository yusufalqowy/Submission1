package com.example.submission1.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission1.data.response.User
import com.example.submission1.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class FollowersViewModel : ViewModel() {

    companion object {
        private const val TAG = "FollowersViewModel"
    }

    val listFollowers = MutableLiveData<ArrayList<User>>()

    fun setListFollowers(username: String) {
        val client = ApiConfig.apiInstance.getFollowers(username)
        client.enqueue(object : retrofit2.Callback<ArrayList<User>> {
            override fun onResponse(
                call: retrofit2.Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if (response.isSuccessful) {
                    listFollowers.postValue(response.body())
                }
            }

            override fun onFailure(call: retrofit2.Call<ArrayList<User>>, t: Throwable) {
                Log.e(TAG, "${t.message.toString()}")
            }
        })
    }


    fun getFollowersList(): LiveData<ArrayList<User>> {
        return listFollowers
    }
}
