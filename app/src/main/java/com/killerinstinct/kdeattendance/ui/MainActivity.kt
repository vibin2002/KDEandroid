package com.killerinstinct.kdeattendance.ui

import android.content.Intent
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
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
import com.killerinstinct.kdeattendance.viewmodels.MainVMProviderFactory
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var fab: TextView
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var day:TextView
    private lateinit var dateView:ConstraintLayout
    private lateinit var ghostView:CardView
    private lateinit var arrowDwn:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.main_recyclerview)
        setupRecyclerView()

        //Date
        var dateTime:String
        var calender=Calendar.getInstance()
        val listOfMonth:List<String> = listOf<String> ("January","February","March","April","May","June","July","August","September","October","November","December")
        day=findViewById(R.id.day)
        dateTime=""+calender.get(Calendar.DAY_OF_MONTH)+" "+listOfMonth[calender.get(Calendar.MONTH)]+", "+calender.get(Calendar.YEAR)
        day.setText(dateTime)

        //Visiblity
        dateView=findViewById(R.id.clickView)
        ghostView=findViewById(R.id.ghostlayout)
        arrowDwn=findViewById(R.id.arrow)
        dateView.setOnClickListener{
            if (ghostView.visibility== View.VISIBLE)
            {
                TransitionManager.beginDelayedTransition(ghostView,AutoTransition())
                ghostView.visibility=(View.GONE)
                arrowDwn.setImageResource(R.drawable.arrow_up_24)
            }
            else
            {
                TransitionManager.beginDelayedTransition(ghostView,AutoTransition())
                ghostView.visibility=(View.VISIBLE)
                arrowDwn.setImageResource(R.drawable.ic_round_keyboard_arrow_down_24)

            }

        }




        val mainRepository = MainRepository(KDEdatabase(this, Utils.ALL_EMPLOYEES))
        val viewModelProviderFactory = MainVMProviderFactory(mainRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

        viewModel.employeeListLiveData.observe(this,{
            recyclerView.adapter = MainRecyclerAdapter(it)
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