package com.example.internshala.room

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface WorkshopDAO {

    @Insert(onConflict=OnConflictStrategy.IGNORE)
    suspend fun insert(EWorkshop: EWorkshop)

    @Query("select * from workshop_table")
    fun getAll(): LiveData<List<EWorkshop>>

    @Query("Delete from workshop_table  where id=:uid")
    suspend fun delete(uid:Long)


}