package com.example.notas

import android.app.Application
import com.example.notas.data.AppContainer
import com.example.notas.data.AppDataContainer

//clase que se inicia al empezar a correr la aplicación
class NotasApplication:Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}