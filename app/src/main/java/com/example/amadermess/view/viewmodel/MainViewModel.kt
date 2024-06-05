package com.example.amadermess.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.amadermess.databse.MessDataBase
import com.example.amadermess.model.MessMember
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(val database: MessDataBase? = null) : ViewModel() {


    val demoMessMembers = listOf(
        MessMember(
            name = "John Doe",
            phone = "123-456-7890",
            deposit = 1500.0.toString(),
            currentExpense = 250.75.toString(),
            totalMeal = 10.0.toString()
        ),
        MessMember(
            name = "Jane Smith",
            phone = "987-654-3210",
            deposit = 2000.0.toString(),
            currentExpense = 100.25.toString(),
            totalMeal = 8.0.toString()
        ),
        MessMember(
            name = "Alice Johnson",
            phone = "555-666-7777",
            deposit = 500.0.toString(),
            currentExpense = 300.0.toString(),
            totalMeal = 5.0.toString()
        )
    )

    var member : MessMember? = null


    var _messMemberList : List<MessMember>? = emptyList()

    init {
        insertMembers(demoMessMembers)
        getMessMembers()
    }
    fun insertIntoMessDb(messMember: MessMember) {
        Log.e("I am inserting", messMember.toString())
        viewModelScope.launch(Dispatchers.IO) {
            database?.messDao()?.insert(messMember)
        }
    }

    fun getMessMembers() {
        viewModelScope.launch(Dispatchers.IO) {
            _messMemberList = database?.messDao()?.getMessMembers()!!
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