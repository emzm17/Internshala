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
import com.example.internshala.SelectListener
import com.example.internshala.room.EWorkshop

class Adapter(private val list:ArrayList<EWorkshop>,private val context: Context,private val selectListener: SelectListener): RecyclerView.Adapter<Adapter.DashViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.dash_item,parent,false)
        return DashViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DashViewHolder, position: Int) {
         holder.workshopname.text=list[position].name
         if(list[position].button){
              holder.applyBtn.text="Applied"
         }
         if(!list[position].button){
             holder.applyBtn.setOnClickListener {
                 selectListener.onItemClick(position)
             }
         }

    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }
    override fun getItemCount(): Int {
        return list.size
    }
    class DashViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val workshopname:TextView=itemView.findViewById(R.id.workshopName)
        val applyBtn:Button=itemView.findViewById(R.id.applyBtn)
    }

}

