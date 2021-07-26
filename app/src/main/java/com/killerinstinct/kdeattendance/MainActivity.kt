package com.killerinstinct.kdeattendance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val employeeList = listOf(
            Employee(1,"Vibin",25),
            Employee(2,"Nitin",25),
            Employee(3,"Nitin",25),
            Employee(4,"Nitin",25),
            Employee(5,"Nitin",25),
            Employee(6,"Nitin",25),
            Employee(7,"Nitin",25),
            Employee(8,"Nitin",25),
        )
//        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerview)
//        recyclerView.adapter = MainRecyclerAdapter(employeeList)

    }
}