package com.example.amadermess.databse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.amadermess.model.MessMember

@Database(entities = [MessMember::class], version = 1, exportSchema = false)
abstract class MessDataBase : RoomDatabase() {
    abstract fun messDao(): MessMembersDao

    companion object {
        @Volatile private var databaseInstance: MessDataBase? = null

        fun getInstance(context: Context): MessDataBase {

            return databaseInstance ?: synchronized(this) {
                databaseInstance ?: buildDataBase(context).also { databaseInstance = it }
            }
        }

        private fun buildDataBase(context: Context): MessDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                MessDataBase::class.java, "sharetrip-voucher-db"
            ).fallbackToDestructiveMigration().build()
        }
    }
}



