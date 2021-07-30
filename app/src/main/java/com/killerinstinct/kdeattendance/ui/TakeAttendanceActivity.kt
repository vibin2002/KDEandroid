package com.killerinstinct.kdeattendance.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.killerinstinct.kdeattendance.R
import com.killerinstinct.kdeattendance.Utils
import com.killerinstinct.kdeattendance.adapters.TakeAttendanceRecAdapter
import com.killerinstinct.kdeattendance.localdb.KDEdatabase
import com.killerinstinct.kdeattendance.models.Attendand
import com.killerinstinct.kdeattendance.repository.MainRepository
import com.killerinstinct.kdeattendance.viewmodels.TakeAttendanceVMProviderFactory
import com.killerinstinct.kdeattendance.viewmodels.TakeAttendanceViewModel

class TakeAttendanceActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: TakeAttendanceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_attendance)

        recyclerView = findViewById<RecyclerView>(R.id.TA_rec)
        setupRecyclerView()

        val mainRepository = MainRepository(KDEdatabase(this,Utils.ALL_EMPLOYEES))
        val viewModelProviderFactory = TakeAttendanceVMProviderFactory(mainRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(TakeAttendanceViewModel::class.java)

        viewModel.employeeListLiveData.observe(this,{
            val attendantList = it.sortedBy { e ->
                e.id
            }.map { emp ->
                Attendand(emp.id,emp.name,emp.category,false)
            }
            recyclerView.adapter = TakeAttendanceRecAdapter(attendantList).also { attendance ->
                findViewById<Button>(R.id.btn_finish).setOnClickListener {
                    viewModel.attendance.postValue(attendance.getList())
                }
            }
        })

        viewModel.attendance.observe(this,{
            if (it.isEmpty())
                Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show()
            else
            {
                //it -> list of attendance to be saved
                it.forEach { attendand ->
                    Log.d("KEDODA", "$attendand\n")
                }
            }
        })

    }

    private fun setupRecyclerView() {
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager =  LinearLayoutManager(this)


    }
}