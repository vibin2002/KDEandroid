package com.killerinstinct.kdeattendance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.killerinstinct.kdeattendance.models.Attendand
import com.killerinstinct.kdeattendance.R

class TakeAttendanceRecAdapter (
    private var AttendantList: List<Attendand>
): RecyclerView.Adapter<TakeAttendanceRecAdapter.AttendantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendantViewHolder {
        return AttendantViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.attendance_list,parent,false)
        )
    }

    override fun onBindViewHolder(holder: AttendantViewHolder, position: Int) {
        holder.takeAttName.text = AttendantList[position].name
        holder.takeAttId.text = (position+1).toString()
        holder.checkbox.setOnCheckedChangeListener { _, b ->
            AttendantList[position].isPresent = b
        }
//        holder.cardView.setCardBackgroundColor(getColor(position))


    }

    fun getList() : List<Attendand>{
        return AttendantList
    }

    override fun getItemCount() = AttendantList.size

    inner class AttendantViewHolder(view: View): RecyclerView.ViewHolder(view){
        val takeAttId= view.findViewById(R.id.ID) as TextView
        val takeAttName = view.findViewById(R.id.name) as TextView
        val checkbox=view.findViewById(R.id.check) as CheckBox
//        val cardView=view.findViewById(R.id.attendance_cardview) as CardView

    }
}