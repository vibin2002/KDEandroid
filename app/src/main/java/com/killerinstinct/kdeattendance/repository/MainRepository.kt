package com.killerinstinct.kdeattendance.repository

import com.killerinstinct.kdeattendance.localdb.KDEdatabase
import com.killerinstinct.kdeattendance.models.Employee

class MainRepository(
    private val database: KDEdatabase
) {

    suspend fun addEmployee(employee: Employee) = database.employeeDao().addEmployee(employee)

}