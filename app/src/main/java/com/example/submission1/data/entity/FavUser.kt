package com.example.submission1.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorit_user")
@Parcelize
data class FavUser(

    @ColumnInfo(name = "avatarUrl")
    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @PrimaryKey
    @ColumnInfo(name = "login")
    @field:SerializedName("login")
    val login: String,

) : Parcelable
