package com.example.notas.crearNotaScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class NotasViewModel : ViewModel() {
    var nota by mutableStateOf("")
        private set

    var isTask by mutableStateOf(false)
        private set

    fun onNotaChange(it: String) {
        nota = it
    }

    fun onIsTaskCange(it: Boolean) {
        isTask = it
    }
}