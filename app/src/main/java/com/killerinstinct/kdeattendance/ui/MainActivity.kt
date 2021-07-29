package com.killerinstinct.kdeattendance.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
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

class MainActivity : AppCompatActivity(),SetupRVonBSDismiss {
    private lateinit var fab: ExtendedFloatingActionButton
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainRepository = MainRepository(KDEdatabase(this, Utils.ALL_EMPLOYEES))
        val viewModelProviderFactory = MainViewModelProviderFactory(mainRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

        fab = findViewById(R.id.extendedFloatingActionButton)
        fab.setOnClickListener {
            val i = Intent(this, TakeAttendanceActivity::class.java)
            startActivity(i)
        }

        val bottomSheetDialog = AddEmployeeBottomSheet()
        findViewById<CardView>(R.id.btn_add_employee).setOnClickListener {
            bottomSheetDialog.show(supportFragmentManager, "MainActivity")
            setupRecyclerView()
        }
        setupRecyclerView()

    }


    internal fun setupRecyclerView() {
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerview)
        recyclerView.addItemDecoration(dividerItemDecoration)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        val kdEdatabase = KDEdatabase(this, Utils.ALL_EMPLOYEES)
        CoroutineScope(Dispatchers.Main).launch {
            val list = kdEdatabase.employeeDao().getAllEmployees()
            recyclerView.adapter = MainRecyclerAdapter(list)
            Log.d("WandaVision", list.toString())
        }
    }

    override fun updateRecyclerView() {
        setupRecyclerView()
    }
}