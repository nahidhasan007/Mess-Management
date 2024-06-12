package com.example.amadermess.databse

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.amadermess.model.MessMember
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Dao
interface MessMembersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(messMember: MessMember): Long

    @Query("SELECT * FROM mess_members")
    fun getMessMembers(): List<MessMember>

    @Delete
    fun delete(messMember: MessMember)
}