package com.killerinstinct.kdeattendance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TakeAttendanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_attendance)

        val tarecyclerView = findViewById<RecyclerView>(R.id.TA_rec)
        val linearLayoutManager = LinearLayoutManager(this)
        tarecyclerView.apply {
            adapter =TakeAttendanceRecAdapter(Utils.attendance_list)
            layoutManager = linearLayoutManager
        }
    }
}