package com.example.notas.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.notas.data.NotasEntity
import com.example.notas.data.NotasRepository


class NotasViewModel(private val notasRepository: NotasRepository) : ViewModel() {

    /** Esta es una variable que almacena el estado de la interfaz de usuario de las notas
     * Se usa mutableStateOf para que el estado sea observable y se actualice automáticamente cuando cambie*/
    var uiState by mutableStateOf(NotaState())
        private set /**Se usa private set para que solo el modelo de vista pueda modificar el estado*/

    /**Esta es una función que actualiza el estado de la interfaz de usuario con un nuevo estado*/
    fun updateUiState(notaState: NotaState) {
        uiState = notaState
    }

    /** Esta es una función que guarda una nota en el repositorio de notas
     * Se usa suspend para que la función se ejecute de forma asíncrona y no bloquee el hilo*/
    suspend fun saveNote() {
        // Se valida la entrada del usuario antes de guardar la nota
        if (validateInput()) {
            // Se convierte el estado de la interfaz de usuario en una nota y se inserta en el repositorio
            notasRepository.insertNote(uiState.toNote())
        }
    }

    /** Esta es una función privada que valida la entrada del usuario
     * Se usa un parámetro opcional _uiState para poder pasar un estado diferente al actual si se desea*/
    private fun validateInput(_uiState: NotaState = uiState): Boolean {
        return with(_uiState) {
            nota.isNotBlank()
        }
    }

}

data class NotaState(
    val id: Int = 0,
    val titulo: String = "",
    val nota: String = "",
    val isTask: Boolean = false,
    val fecha: String = "",
    val hora: String = "",
)

fun NotaState.toNote(): NotasEntity = NotasEntity(
    id = id,
    titulo = titulo,
    nota = nota,
    tarea = isTask,
    fecha = fecha,
    hora = hora
)