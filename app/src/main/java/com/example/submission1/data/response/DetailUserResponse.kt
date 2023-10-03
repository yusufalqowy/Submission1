package com.example.submission1.data.response

data class DetailUserResponse(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val following_url: String,
    val followers_url: String,
    val name: String,
    val following: Int,
    val followers: Int
)
