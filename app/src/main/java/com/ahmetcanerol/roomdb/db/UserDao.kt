package com.ahmetcanerol.roomdb.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ahmetcanerol.roomdb.model.User

@Insert(onConflict = OnConflictStrategy.IGNORE)
fun addUser(user: User)

@Update
fun updateUser(user: User)

@Delete
fun deleteUser(user: User)

@Query("DELETE FROM user_tb WHERE id = :id")
fun deleteById(id: Int)


@Dao
interface UserDao {
    @Query(value = "SELECT * FROM user_tb ORDER BY year ASC")
    fun readAllData() : LiveData<List<User>>


}