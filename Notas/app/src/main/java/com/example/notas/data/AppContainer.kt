package com.example.notas.data

import android.content.Context

//definir los repositorys que utilizaran los viewmodels
interface AppContainer {
    val notasRepository: NotasRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val notasRepository: NotasRepository by lazy {
        OfflineNotasRepository(NotasDB.getDatabase(context).notaDAO())
    }
}