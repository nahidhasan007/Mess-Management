package com.example.amadermess.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amadermess.databse.MessDataBase
import com.example.amadermess.model.MessMember
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(val database: MessDataBase? = null) : ViewModel() {

    var member : MessMember? = null

    val messMemberList = MutableStateFlow<MutableList<MessMember>>(mutableListOf())

    private val _messMemberList = MutableLiveData<List<MessMember>>()
    val messMembers : LiveData<List<MessMember>> get() = _messMemberList
    init {
        //insertMembers(demoMessMembers)
    }
    fun insertIntoMessDb(messMember: MessMember) {
        Log.e("I am inserting", messMember.toString())
        viewModelScope.launch(Dispatchers.IO) {
            database?.messDao()?.insert(messMember)
        }
    }

    fun getMessMembers() {
        viewModelScope.launch(Dispatchers.IO) {
            messMemberList.value = (database?.messDao()?.getMessMembers()!!)
//            _messMemberList.value = database.messDao()?.getMessMembers()!!
        }
    }

    fun deleteMembers(member: MessMember) {
        Log.e("Deleting", "I am deleting a member")
        viewModelScope.launch(Dispatchers.IO) {
            val deletedCount = database?.messDao()?.delete(member)
            if(deletedCount==1){
                val arrayList = ArrayList(messMemberList.value)
                Log.e("I am updating v1", "$arrayList")
                arrayList.remove(member)
                Log.e("I am updating v2", "$arrayList")
                messMemberList.emit(arrayList)
            }
        }
    }

    fun insertMembers(members : List<MessMember>) {
        Log.e("I am updating", "Updating$members")
       for(member in members){
           insertIntoMessDb(member)
       }
    }

}

class MainVMF(val database: MessDataBase? = null) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(database) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}