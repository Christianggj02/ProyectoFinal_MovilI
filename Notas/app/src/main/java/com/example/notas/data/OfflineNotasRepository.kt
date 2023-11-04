package com.example.notas.data

import kotlinx.coroutines.flow.Flow

class OfflineNotasRepository(private val notaDao: NotasDAO) : NotasRepository{
    override fun getAllNotesStream(): Flow<List<NotasEntity>> = notaDao.getAllItems()
    override fun getNoteStream(id: Int): Flow<NotasEntity?> = notaDao.getItem(id)
    override fun getAll(isTask: Boolean): Flow<List<NotasEntity>> = notaDao.getAll(isTask)
    override suspend fun insertNote(notaEntity: NotasEntity) = notaDao.insert(notaEntity)
    override suspend fun deleteNote(notaEntity: NotasEntity) = notaDao.delete(notaEntity)
    override suspend fun updateNote(notaEntity: NotasEntity) = notaDao.update(notaEntity)
}