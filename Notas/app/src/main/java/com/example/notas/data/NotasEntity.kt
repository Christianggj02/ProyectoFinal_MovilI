package com.example.notas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notas")
data class NotasEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int =0 ,
    val titulo:String,
    val nota:String,
    val tarea: Boolean,
    val fecha:String,
    val hora:String
)