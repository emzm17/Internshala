package com.example.internshala.repository

import com.example.internshala.room.EWorkshop
import com.example.internshala.room.WorkshopDatabase

class Repository(private val workshopDatabase: WorkshopDatabase) {

    suspend fun insert(EWorkshop: EWorkshop){
        workshopDatabase.workshopdao().insert(EWorkshop)
    }

    fun getAll()=workshopDatabase.workshopdao().getAll()

    suspend fun deleteAll(){
        workshopDatabase.workshopdao().deleteAll()
    }


}