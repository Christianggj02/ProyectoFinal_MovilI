package com.example.notas.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotasDAO {
    @Insert
    suspend fun insert(notaEntity:NotasEntity)
    @Update
    suspend fun update(notaEntity:NotasEntity)
    @Delete
    suspend fun delete(notaEntity:NotasEntity)

    @Query("SELECT * from Notas WHERE id = :id")
    fun getItem(id: Int): Flow<NotasEntity>

    @Query("SELECT * from Notas ORDER BY fecha ASC")
    fun getAllItems(): Flow<List<NotasEntity>>

    @Query("SELECT * from Notas WHERE tarea = :isTask")
    fun getAll(isTask: Boolean): Flow<List<NotasEntity>>
}