package com.killerinstinct.kdeattendance.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Attendand(

    @PrimaryKey
    val id:Int,
    val name:String,
    val category: String,
    var isPresent: Boolean = false
){




}
