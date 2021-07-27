package com.killerinstinct.kdeattendance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private  lateinit var fab:ExtendedFloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab=findViewById(R.id.extendedFloatingActionButton)
        fab.setOnClickListener{
            val i=Intent(this,TakeAttendanceActivity::class.java)
            startActivity(i)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerview)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.apply {
            adapter = MainRecyclerAdapter(Utils.employeeList)
            layoutManager = linearLayoutManager
        }



    }
}