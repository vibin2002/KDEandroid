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
        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerview)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.apply {
            adapter = MainRecyclerAdapter(Utils.employeeList)
            layoutManager = linearLayoutManager
        }


        findViewById<CardView>(R.id.btn_add_employee).setOnClickListener {



            CoroutineScope(Dispatchers.IO).launch {
                KDEdatabase(applicationContext).employeeDao().addEmployee(Utils.employeeList[1])
            }
        }

        val kdEdatabase = KDEdatabase(this)
        findViewById<TextView>(R.id.textView).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val list = kdEdatabase.employeeDao().getAllEmployees()
                Log.d("WandaVision", list.toString())
            }


        }
    }
}