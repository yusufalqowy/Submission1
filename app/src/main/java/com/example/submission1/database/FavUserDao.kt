package com.example.submission1.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.submission1.data.entity.FavUser

@Dao
interface FavUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addToFavorit(favoritUser: FavUser)

    @Query("SELECT * FROM favorit_user")
    fun getFavUser(): LiveData<List<FavUser>>

    @Delete
    fun delete(favoritUser: FavUser)

    @Query("SELECT * FROM favorit_user WHERE login = :username")
    fun getFavUserByUsername(username: String): LiveData<List<FavUser>>

}
