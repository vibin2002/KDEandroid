package com.killerinstinct.kdeattendance.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.killerinstinct.kdeattendance.*
import com.killerinstinct.kdeattendance.adapters.MainRecyclerAdapter
import com.killerinstinct.kdeattendance.localdb.KDEdatabase
import com.killerinstinct.kdeattendance.models.Employee
import com.killerinstinct.kdeattendance.repository.MainRepository
import com.killerinstinct.kdeattendance.viewmodels.MainViewModel
import com.killerinstinct.kdeattendance.viewmodels.MainViewModelProviderFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var fab: ExtendedFloatingActionButton
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainRepository = MainRepository(KDEdatabase(this))
        val viewModelProviderFactory = MainViewModelProviderFactory(mainRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

        fab = findViewById(R.id.extendedFloatingActionButton)
        fab.setOnClickListener {
            val i = Intent(this, TakeAttendanceActivity::class.java)
            startActivity(i)
        }

        findViewById<CardView>(R.id.btn_add_employee).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.addEmployee(Employee("Jon Snow","ABC"))
            }
            setupRecyclerView()
        }
        setupRecyclerView()

    }


    fun setupRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerview)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        val kdEdatabase = KDEdatabase(this)
        CoroutineScope(Dispatchers.IO).launch {
            val list = kdEdatabase.employeeDao().getAllEmployees()
            recyclerView.adapter = MainRecyclerAdapter(list)
            Log.d("WandaVision", list.toString())
        }
    }
}