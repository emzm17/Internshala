package com.example.internshala.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internshala.adapter.Adapter
import com.example.internshala.R
import com.example.internshala.SelectListener
import com.example.internshala.repository.Repository
import com.example.internshala.room.EWorkshop
import com.example.internshala.room.WorkshopDatabase
import com.example.internshala.viewmodel.WorkshopViewModel
import com.example.internshala.viewmodel.WorkshopViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton


class Workshop : Fragment(),SelectListener  {
    private lateinit var vm:WorkshopViewModel
    private lateinit var repository: Repository
    private lateinit var database: WorkshopDatabase
    private lateinit var workshoplist:ArrayList<EWorkshop>
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var adapter: Adapter
    private lateinit var rcview:RecyclerView
    private lateinit var dashboardBtn:FloatingActionButton

    override fun onAttach(context: Context) {
        preferences = context.getSharedPreferences("userFile", Context.MODE_PRIVATE)
        editor = preferences.edit()

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        ( activity as AppCompatActivity ).supportActionBar?.title="Workshop"
        return inflater.inflate(R.layout.fragment_workshop, container, false)
    }



    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
         database=WorkshopDatabase.getDatabase(requireContext())
         repository= Repository(database)
         rcview=view.findViewById(R.id.recycler_view1)
         vm=ViewModelProvider(this,WorkshopViewModelFactory(repository))[WorkshopViewModel::class.java]
         vm.insert(EWorkshop(1,"DSA"))
         vm.insert(EWorkshop(2,"Android Development"))
         vm.insert(EWorkshop(3,"Web Development"))
         workshoplist=ArrayList()
         dashboardBtn=view.findViewById(R.id.dashboardBtn)
         dashboardBtn.setOnClickListener {
             dashboardclick()
         }

         vm.getAll().observe(viewLifecycleOwner) { it->
             if (!it.isNullOrEmpty()) {
                 workshoplist.clear()
                 it.forEach { i->
                       workshoplist.add(i)
                 }

             }
             adapter.notifyDataSetChanged()
         }
        adapter= Adapter(workshoplist,requireContext(),this)
        rcview.layoutManager=LinearLayoutManager(requireContext())
        rcview.adapter=adapter
    }

    private fun dashboardclick() {
        if(!preferences.contains("userName")){
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.nav_container,Login()).commit()
        }
       requireActivity().supportFragmentManager.beginTransaction().replace(R.id.nav_container,Dashboard()).commit()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onItemClick(position:Int) {
        if(!preferences.contains("userName")){
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.nav_container,Login()).commit()
        }
         val currentitem=workshoplist[position]
         vm.delete(adapter.getItemId(position))
         currentitem.button=true
         vm.insert(currentitem)
        vm.getAll().observe(viewLifecycleOwner) { it->
            if (!it.isNullOrEmpty()) {
                workshoplist.clear()
                it.forEach { i->
                    workshoplist.add(i)
                }

            }
            adapter.notifyDataSetChanged()
        }
    }


}