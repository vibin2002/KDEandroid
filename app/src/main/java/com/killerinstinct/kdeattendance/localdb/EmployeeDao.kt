package com.killerinstinct.kdeattendance.localdb

import androidx.room.*
import com.killerinstinct.kdeattendance.models.Employee

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEmployee(employee: Employee)

//    @Delete
//    suspend fun deleteEmployee()

    @Query("SELECT * FROM Employee ORDER BY name ASC")
    suspend fun getAllEmployees(): List<Employee>

}