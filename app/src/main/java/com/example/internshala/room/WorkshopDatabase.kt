package com.example.internshala.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EWorkshop::class], version = 1, exportSchema = false)
abstract class WorkshopDatabase :RoomDatabase(){
    abstract fun workshopdao(): WorkshopDAO
    companion object {
        private var INSTANCE: WorkshopDatabase? = null
        fun getDatabase(context: Context): WorkshopDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                       Room.databaseBuilder(context,WorkshopDatabase::class.java,"workshopDB").build()
                }
            }
            return INSTANCE!!
        }
    }
}