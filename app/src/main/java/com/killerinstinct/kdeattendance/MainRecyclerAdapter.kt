package com.killerinstinct.kdeattendance

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainRecyclerAdapter(
    private val employeeList: List<Employee>
): RecyclerView.Adapter<MainRecyclerAdapter.EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_employee_main,parent,false)
        )
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.tvname.text = employeeList[position].name
        holder.tvid.text = employeeList[position].id.toString()
        holder.progressBar.progress = employeeList[position].progress
    }

    override fun getItemCount() = employeeList.size

    inner class EmployeeViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvid = view.findViewById(R.id.tv_eid) as TextView
        val tvname = view.findViewById(R.id.tv_ename) as TextView
        val progressBar = view.findViewById(R.id.stats_progressbar) as ProgressBar

    }
}