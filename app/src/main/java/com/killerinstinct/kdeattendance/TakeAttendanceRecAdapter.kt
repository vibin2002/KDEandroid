package com.killerinstinct.kdeattendance

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class TakeAttendanceRecAdapter (
    private val AttendantList: List<Attendand>
): RecyclerView.Adapter<TakeAttendanceRecAdapter.AttendantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendantViewHolder {
        return AttendantViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.attendance_list,parent,false)
        )
    }

    override fun onBindViewHolder(holder: AttendantViewHolder, position: Int) {
        holder.takeAttName.text = AttendantList[position].name
        holder.takeAttId.text = AttendantList[position].id.toString()
//        holder.cardView.setCardBackgroundColor(getColor(position))


    }

//    private fun getColor(position: Int): Any {
//        val status:Boolean=
//
//    }

    override fun getItemCount() = AttendantList.size

    inner class AttendantViewHolder(view: View): RecyclerView.ViewHolder(view){
        val takeAttId= view.findViewById(R.id.ID) as TextView
        val takeAttName = view.findViewById(R.id.name) as TextView
//        val checkisTrue=view.findViewById(R.id.check) as CheckBox
//        val cardView=view.findViewById(R.id.attendance_cardview) as CardView

    }
}