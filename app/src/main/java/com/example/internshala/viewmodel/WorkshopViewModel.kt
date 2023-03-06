package com.example.internshala.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internshala.fragments.Workshop
import com.example.internshala.repository.Repository
import com.example.internshala.room.EWorkshop
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkshopViewModel(private val repository: Repository) :ViewModel(){

     fun insert(EWorkshop:EWorkshop){
         viewModelScope.launch(Dispatchers.IO) {
              repository.insert(EWorkshop)
         }
     }
    fun getAll():LiveData<List<EWorkshop>>{
          return repository.getAll()
    }

    fun delete(uid:Long){
         viewModelScope.launch(Dispatchers.IO) {
             repository.delete(uid)
         }
    }
}