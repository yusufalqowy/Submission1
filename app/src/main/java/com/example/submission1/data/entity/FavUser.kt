package com.example.submission1.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorit_user")
@Parcelize
data class FavUser(
    val login: String,
    @PrimaryKey
    val id: Int,
) : Parcelable
