package com.ahmetcanerol.roomdb.db

import android.content.Context
import androidx.room.*
import com.ahmetcanerol.roomdb.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase :RoomDatabase() {
    abstract fun userDao():UserDao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null


        fun getDatabase(context: Context): UserDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also {
                        INSTANCE = it }
            }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java, "user_db"
            ).build()

    }

}