package com.example.internshala.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internshala.R
import com.example.internshala.adapter.DashAdapter
import com.example.internshala.repository.Repository
import com.example.internshala.room.EWorkshop
import com.example.internshala.room.WorkshopDatabase
import com.example.internshala.viewmodel.WorkshopViewModel
import com.example.internshala.viewmodel.WorkshopViewModelFactory

class Dashboard : Fragment() {
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var rcview:RecyclerView
    private lateinit var adapter:DashAdapter
    private lateinit var list:ArrayList<EWorkshop>
    private lateinit var vm: WorkshopViewModel
    private lateinit var repository: Repository
    private lateinit var database: WorkshopDatabase




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        ( activity as AppCompatActivity).supportActionBar?.title="Dashboard"
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        database=WorkshopDatabase.getDatabase(requireContext())
        repository= Repository(database)
        list=ArrayList()
        rcview=view.findViewById(R.id.recycler_view2)
        adapter= DashAdapter(list,requireContext())
        vm= ViewModelProvider(this, WorkshopViewModelFactory(repository))[WorkshopViewModel::class.java]
        vm.getAll().observe(viewLifecycleOwner) { it->
            if (!it.isNullOrEmpty()) {
                list.clear()
                it.forEach { i->
                     if(i.button){
                          list.add(i)
                     }
                }

            }
            adapter.notifyDataSetChanged()
        }

        rcview.layoutManager=LinearLayoutManager(requireContext())
        rcview.adapter=adapter
    }

}