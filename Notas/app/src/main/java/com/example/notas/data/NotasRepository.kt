package com.example.notas.data

import kotlinx.coroutines.flow.Flow

interface NotasRepository {
    /** Retorna todos las notas de la fuente de datos dada.*/
    fun getAllNotesStream(): Flow<List<NotasEntity>>
    /** Retorna una note de la fuente de datos dada que concide con el id entregado*/
    fun getNoteStream(id: Int): Flow<NotasEntity?>
    /** Retorna todas las notas o tareas de la fuente de datos segun el parametro true o false*/
    fun getAll(isTask: Boolean): Flow<List<NotasEntity>>
    /** Insertar una nota en la fuente de datos*/
    suspend fun insertNote(notaEntity: NotasEntity)
    /**Borrar nota de la fuente de datos*/
    suspend fun deleteNote(notaEntity: NotasEntity)
    /**Actualizar nota de la fuente de datos**/
    suspend fun updateNote(notaEntity: NotasEntity)
}