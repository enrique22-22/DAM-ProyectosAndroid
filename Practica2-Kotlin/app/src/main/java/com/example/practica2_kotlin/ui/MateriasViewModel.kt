package com.example.practica2_kotlin.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.practica2_kotlin.modelo.MateriasUIState
import com.example.practica2_kotlin.modelo.Tarea
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MateriasViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MateriasUIState())

    val uiState: StateFlow<MateriasUIState> = _uiState.asStateFlow()

    var tituloUs by mutableStateOf("")
        private set

    var descripcionUs by mutableStateOf("")
        private set

    var tipoModulo by mutableStateOf("")
        private set

    var urgencia by mutableStateOf(false)
        private set

    fun actualizarEleccionUrgencia(urgente: Boolean) {
        urgencia = urgente

        val tareaViewModel = _uiState.value.tareaUIState.copy()
        tareaViewModel.esUrgente = urgencia

        _uiState.update { estadoActual ->
            estadoActual.copy (
                tareaUIState = tareaViewModel
            )
        }
    }

    fun actualizarModulo(modulos: String) {
        tipoModulo = modulos

        val tareaViewModel = _uiState.value.tareaUIState.copy()
        tareaViewModel.modulos = tipoModulo

        _uiState.update { estadoActual ->
            estadoActual.copy (
                tareaUIState = tareaViewModel
            )
        }
    }

    fun actualizarTitulo(titulo: String) {
        tituloUs = titulo

        val tareaViewModel = _uiState.value.tareaUIState.copy()
        tareaViewModel.tituloTarea = tituloUs

        _uiState.update { estadoActual ->
            estadoActual.copy (
                tareaUIState = tareaViewModel
            )
        }

    }

    fun actualizarDescripcion(descripcion: String) {
        descripcionUs = descripcion

        val tareaViewModel = _uiState.value.tareaUIState.copy()
        tareaViewModel.descripcion = descripcionUs

        _uiState.update { estadoActual ->
            estadoActual.copy (
                tareaUIState = tareaViewModel
            )
        }
    }


    fun nuevaTarea() {

        if (tituloUs.isBlank() || descripcionUs.isBlank() || tipoModulo.isBlank()) {
            return
        }

        _uiState.update { estadoActual ->
            estadoActual.copy(
                tareaUIState = Tarea(),
                tareas = estadoActual.tareas + estadoActual.tareaUIState
            )
        }

        tituloUs = ""
        descripcionUs = ""
        tipoModulo = ""
        urgencia = false
    }

}