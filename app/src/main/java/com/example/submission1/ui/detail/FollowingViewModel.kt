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

class FollowingViewModel : ViewModel() {

    companion object{
        private const val TAG ="FollowingViewModel"
    }

     val listFollowing = MutableLiveData<ArrayList<User>>()

    fun setListFollowing(username: String){
        val client = ApiConfig.apiInstance.getFollowing(username)
        client.enqueue(object : retrofit2.Callback<ArrayList<User>>{
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if (response.isSuccessful){
                    listFollowing.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                Log.e(TAG, "${t.message.toString()}")
            }

        })

    }
    fun getListFollowing() : LiveData<ArrayList<User>>{
        return listFollowing
    }
}