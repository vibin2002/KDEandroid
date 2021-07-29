package com.killerinstinct.kdeattendance.repository

import com.killerinstinct.kdeattendance.localdb.KDEdatabase
import com.killerinstinct.kdeattendance.models.Employee

class MainRepository(
    private val database: KDEdatabase
) {

    private val employeeDao = database.employeeDao()

    suspend fun addEmployee(employee: Employee) = employeeDao.addEmployee(employee)

    suspend fun deleteEmployee(id: Int) {
        employeeDao.deleteEmployee(id)
    }

    suspend fun getAllEmployees(): List<Employee> = employeeDao.getAllEmployees()
}