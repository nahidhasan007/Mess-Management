package com.example.amadermess.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "mess_members")
data class MessMember(
    var name: String? = null,
    @PrimaryKey
    var phone: String,
    var deposit: Double? = 0.0,
    var currentExpense: Double? = 0.0,
    var totalMeal: Double? = 0.0
)
