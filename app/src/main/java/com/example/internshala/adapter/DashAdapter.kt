package com.example.internshala.adapter



import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internshala.R

import com.example.internshala.room.EWorkshop

class DashAdapter(private val list:ArrayList<EWorkshop>,private val context: Context): RecyclerView.Adapter<DashAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.dash_item,parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.workshopname.text=list[position].name
        holder.applyBtn.text="Applied"
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val workshopname:TextView=itemView.findViewById(R.id.workshopName)
        val applyBtn:Button=itemView.findViewById(R.id.applyBtn)
    }

}

