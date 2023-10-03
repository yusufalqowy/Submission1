package com.example.submission1.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User(
    val login: String,
    val id:Int,
    val avatar_url:String
): Parcelable
