package com.killerinstinct.kdeattendance.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.killerinstinct.kdeattendance.models.Employee

@Database(entities = [Employee::class],version = 1)
abstract class KDEdatabase: RoomDatabase()
{
    abstract fun employeeDao(): EmployeeDao

    companion object{
        @Volatile
        private var instance: KDEdatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            KDEdatabase::class.java,
            "kde_attendance"
        ).build()

    }

}