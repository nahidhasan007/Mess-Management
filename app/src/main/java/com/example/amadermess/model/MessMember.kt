package com.example.amadermess.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "mess_members")
data class MessMember(
    var name: String? = null,
    @PrimaryKey
    var phone: String,
    var deposit: String? = "",
    var currentExpense: String? = "",
    var totalMeal: String? = ""
)
