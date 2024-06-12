package com.example.amadermess.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amadermess.model.MessMember

class SharedViewModel : ViewModel() {
    var _messMembers = MutableLiveData<List<MessMember>?>()

    fun setCurrentMembers(members: List<MessMember>) {
        _messMembers.value = members
    }
}