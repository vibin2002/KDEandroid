package com.killerinstinct.kdeattendance.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.killerinstinct.kdeattendance.R
import com.killerinstinct.kdeattendance.Utils
import com.killerinstinct.kdeattendance.adapters.MainRecyclerAdapter
import com.killerinstinct.kdeattendance.adapters.TakeAttendanceRecAdapter
import com.killerinstinct.kdeattendance.localdb.KDEdatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TakeAttendanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_attendance)
        setupRecyclerView()

//        val tarecyclerView = findViewById<RecyclerView>(R.id.TA_rec)
//        val linearLayoutManager = LinearLayoutManager(this)
//        tarecyclerView.apply {
//            adapter = TakeAttendanceRecAdapter(Utils.attendance_list)
//            layoutManager = linearLayoutManager
//        }
    }

    private fun setupRecyclerView() {
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val recyclerView = findViewById<RecyclerView>(R.id.TA_rec)
        recyclerView.addItemDecoration(dividerItemDecoration)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        val kdEdatabase = KDEdatabase(this, Utils.ALL_EMPLOYEES)
        CoroutineScope(Dispatchers.Main).launch {
            val list = kdEdatabase.employeeDao().getAllEmployees()
            recyclerView.adapter = MainRecyclerAdapter(list)

        }
    }
}