package com.example.DrinkMaster.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.DrinkMaster.DrinkMasterApplication
import com.example.DrinkMaster.data.review.Review
import com.example.DrinkMaster.data.review.ReviewDao
import com.example.DrinkMaster.data.user.User
import com.example.DrinkMaster.data.user.UserDTO


@Database(entities = [Review::class, User::class], version = 7, exportSchema = true)
abstract class AppLocalDbRepository : RoomDatabase() {
    abstract fun reviewDao(): ReviewDao
    abstract fun userDto(): UserDTO
}

object AppLocalDatabase {
    val db: AppLocalDbRepository by lazy {
        val context = DrinkMasterApplication.Globals.appContext
            ?: throw IllegalStateException("Application context not available")

        Room.databaseBuilder(
            context,
            AppLocalDbRepository::class.java,
            "DrinkMaster"
        ).fallbackToDestructiveMigration()
            .build()
    }
}