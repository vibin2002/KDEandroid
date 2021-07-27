package com.killerinstinct.kdeattendance.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee(
    val name: String,
    val category: String
) {

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

}