package com.example.internshala.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("workshop_table")

data class EWorkshop(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    var name: String ,
    var button: Boolean = false
):Parcelable