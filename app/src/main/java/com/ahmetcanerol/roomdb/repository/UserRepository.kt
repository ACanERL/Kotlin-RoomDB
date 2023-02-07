package com.ahmetcanerol.roomdb.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ahmetcanerol.roomdb.db.UserDao
import com.ahmetcanerol.roomdb.model.User

class UserRepository(private val userDao:UserDao) {

    val alluser:LiveData<List<User>> =userDao.readAllData()

    fun addUser(user:User){
        viewModelFactory {
            userDao.addUser(user)
        }
    }
    fun updateUser(user:User){
         viewModelFactory {
             userDao.updateUser(user)
         }
    }

    fun deleteUser(user:User){
        viewModelFactory {
            userDao.deleteUser(user )
        }
    }
    fun deleteid(id:Int){
        viewModelFactory {
            userDao.deleteById(id)
        }
    }

}