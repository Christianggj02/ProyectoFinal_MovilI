package com.example.notas.viewModels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.notas.NotasApplication

object NotasViewModelProvider{
    val factory = viewModelFactory {
        initializer {
            NotasViewModel(notasApplication().container.notasRepository)
            //NotasViewModel()
        }
    }
}
fun CreationExtras.notasApplication(): NotasApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NotasApplication)