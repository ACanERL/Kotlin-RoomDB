package com.ahmetcanerol.roomdb.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "user_tb")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val username:String?,
    val year:Int
)
