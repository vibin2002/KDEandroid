package com.killerinstinct.kdeattendance

import com.killerinstinct.kdeattendance.models.Attendand
import com.killerinstinct.kdeattendance.localdb.KDEdatabase
import com.killerinstinct.kdeattendance.localdb.EmployeeDao
import com.killerinstinct.kdeattendance.models.Employee
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    const val ALL_EMPLOYEES = "all_employees"
    fun getCurrentDateAndTime(): String = SimpleDateFormat("yyyyMMdd").format(Date())

    val spinnerCategory = listOf(
        "Welder",
        "Cutter",
        "Carpenter",
        "Manager"
    )

    val employeeList = listOf(
        Employee("John snow","A"),
        Employee("Tyrion","B"),
        Employee("Myrcella","C"),
        Employee("Cersei","A"),
        Employee("Robert","A"),
        Employee("Dragon","B"),
        Employee("John snow","A"),
        Employee("John snow","A"),
        Employee("Dany","A")
    )

    val attendance_list= listOf(
        Attendand(3,"John Snow","A"),
        Attendand(4,"John Snow","A"),
        Attendand(5,"Eddard Snow","B"),
        Attendand(1,"John Snow","A"),
        Attendand(6,"John Snow","A"),
        Attendand(7,"John Snow","A"),
        Attendand(2,"John Snow","A"),

        )


}