package com.killerinstinct.kdeattendance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerview)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.apply {
            adapter = MainRecyclerAdapter(Utils.employeeList)
            layoutManager = linearLayoutManager
        }

    }
}