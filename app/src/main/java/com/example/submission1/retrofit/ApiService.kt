package com.example.submission1.retrofit

import com.example.submission1.data.response.DetailUserResponse
import com.example.submission1.data.response.User
import com.example.submission1.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUsers(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}