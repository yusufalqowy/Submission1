package com.example.submission1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.submission1.data.entity.FavUser

@Database(entities = [FavUser::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userGithubDao(): FavUserDao

    companion object {
        @Volatile
        var INSTANCE: UserDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): UserDatabase {
            if (INSTANCE == null) {
                synchronized(UserDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE as UserDatabase
        }
    }
}