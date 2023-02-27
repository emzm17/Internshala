package com.example.internshala.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internshala.R
import com.example.internshala.adapter.DashAdapter
import com.example.internshala.room.EWorkshop

class Dashboard : Fragment() {
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var rcview:RecyclerView
    private lateinit var adapter:DashAdapter
    private lateinit var list:ArrayList<EWorkshop>

    override fun onAttach(context: Context) {
        preferences = context.getSharedPreferences("userFile", Context.MODE_PRIVATE)
        editor = preferences.edit()
        if(!preferences.contains("userName")){
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.nav_container,Login()).commit()
        }
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args=this.arguments
        list= args!!.getSerializable("list") as ArrayList<EWorkshop>
        rcview=view.findViewById(R.id.recycler_view2)
        adapter= DashAdapter(list,requireContext())
        rcview.layoutManager=LinearLayoutManager(requireContext())
        rcview.adapter=adapter
    }

}