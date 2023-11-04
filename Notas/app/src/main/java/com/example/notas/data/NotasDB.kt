package com.example.notas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NotasEntity::class], version = 1, exportSchema = false)
abstract class NotasDB : RoomDatabase() {

    abstract fun notaDAO(): NotasDAO

    companion object {
        @Volatile
        private var Instance: NotasDB? = null

        fun getDatabase(context: Context): NotasDB {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, NotasDB::class.java, "notas_database")
                    .build().also { Instance = it }
            }
        }
    }
}