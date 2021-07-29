package com.killerinstinct.kdeattendance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.killerinstinct.kdeattendance.models.Employee
import com.killerinstinct.kdeattendance.R

class MainRecyclerAdapter(
    private var employeeList: List<Employee>
): RecyclerView.Adapter<MainRecyclerAdapter.EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_employee_main,parent,false)
        )
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.tvname.text = employeeList[position].name
        holder.tvid.text = (position+1).toString()
        holder.tvcategory.text = employeeList[position].category
    }

    override fun getItemCount() = employeeList.size

    fun getEmployee(position: Int): Int{
        return employeeList[position].id
    }

    inner class EmployeeViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvid = view.findViewById(R.id.tv_eid) as TextView
        val tvname = view.findViewById(R.id.tv_ename) as TextView
        val tvcategory = view.findViewById(R.id.tv_category) as TextView

    }
}