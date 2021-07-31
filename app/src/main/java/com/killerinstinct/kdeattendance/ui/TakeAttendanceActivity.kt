package com.killerinstinct.kdeattendance.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
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
import java.io.File
import java.io.FileOutputStream
import java.util.*

class TakeAttendanceActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: TakeAttendanceViewModel
    private lateinit var day: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_attendance)

        recyclerView = findViewById<RecyclerView>(R.id.TA_rec)
        setupRecyclerView()

        //Date
        var dateTime:String
        var calender= Calendar.getInstance()
        val listOfMonth:List<String> = listOf<String> ("January","February","March","April","May","June","July","August","September","October","November","December")
        day=findViewById(R.id.day)
        dateTime=""+calender.get(Calendar.DAY_OF_MONTH)+" "+listOfMonth[calender.get(Calendar.MONTH)]+", "+calender.get(
            Calendar.YEAR)
        day.setText(dateTime)

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

                var string = ""
                for (i in it.indices)
                {
                    string += "${it[i].name},${it[i].category},${it[i].isPresent}\n"
                }

                try {
                    val fileOutputStream = openFileOutput("${Utils.getCurrentDateAndTime()}.csv",Context.MODE_PRIVATE)
                    fileOutputStream.write(string.toByteArray())
                    fileOutputStream.close()

                    val file = File(filesDir,"${Utils.getCurrentDateAndTime()}.csv")
                    val path = FileProvider.getUriForFile(this,"com.killerinstinct.kdeattendance.fileprovider",file)
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/csv"
                        putExtra(Intent.EXTRA_SUBJECT,"Data")
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        putExtra(Intent.EXTRA_STREAM,path)
                        startActivity(Intent.createChooser(this,"Send file"))
                    }


                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        })

    }

    private fun setupRecyclerView() {
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager =  LinearLayoutManager(this)


    }
}