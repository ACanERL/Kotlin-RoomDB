package com.ahmetcanerol.roomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ahmetcanerol.roomdb.db.UserDatabase
import com.ahmetcanerol.roomdb.model.User
import com.ahmetcanerol.roomdb.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {

    val readAllData:LiveData<List<User>>
    private val userRepository:UserRepository

    init {
        val userDao=UserDatabase.getDatabase(application).userDao()
        userRepository= UserRepository(userDao)
        readAllData=userDao.readAllData()

    }

    fun addUser(user:User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }

    }
    fun updateUser(user:User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(user)
        }
    }
    fun deleteUser(user:User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(user)
        }
    }

    fun deleteId(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteid(id)
        }
    }



}