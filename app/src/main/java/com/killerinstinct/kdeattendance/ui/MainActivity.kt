package com.killerinstinct.kdeattendance.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.killerinstinct.kdeattendance.*
import com.killerinstinct.kdeattendance.adapters.MainRecyclerAdapter
import com.killerinstinct.kdeattendance.localdb.KDEdatabase
import com.killerinstinct.kdeattendance.repository.MainRepository
import com.killerinstinct.kdeattendance.viewmodels.MainViewModel
import com.killerinstinct.kdeattendance.viewmodels.MainViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private lateinit var fab: ExtendedFloatingActionButton
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.main_recyclerview)
        setupRecyclerView()


        val mainRepository = MainRepository(KDEdatabase(this, Utils.ALL_EMPLOYEES))
        val viewModelProviderFactory = MainViewModelProviderFactory(mainRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

        viewModel.employeeListLiveData.observe(this,{
            recyclerView.adapter = MainRecyclerAdapter(it.sortedBy { employee ->
                employee.id
            })
        })

        fab = findViewById(R.id.extendedFloatingActionButton)
        fab.setOnClickListener {
            val i = Intent(this, TakeAttendanceActivity::class.java)
            startActivity(i)
        }

        val bottomSheetDialog = AddEmployeeBottomSheet()
        findViewById<CardView>(R.id.btn_add_employee).setOnClickListener {
            bottomSheetDialog.show(supportFragmentManager, "MainActivity")
        }

    }


    private fun setupRecyclerView() {
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteEmployee(viewModel.employeeListLiveData.value!![viewHolder.adapterPosition].id)
                Toast.makeText(this@MainActivity, "Removed", Toast.LENGTH_SHORT).show()
            }

        }
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)
    }


}