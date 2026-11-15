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
        _uiState.update { estadoActual ->
            estadoActual.copy(
                tareaUIState = estadoActual.tareaUIState.copy(
                    esUrgente = urgente
                )
            )
        }
    }

    fun actualizarModulo(modulos: String) {
        tipoModulo = modulos
        _uiState.update { estadoActual ->
            estadoActual.copy(
                tareaUIState = estadoActual.tareaUIState.copy(
                    modulos = modulos
                )
            )
        }
    }

    fun actualizarTitulo(titulo: String) {
        tituloUs = titulo
        _uiState.update { estadoActual ->
            estadoActual.copy(
                tareaUIState = estadoActual.tareaUIState.copy(
                    tituloTarea = titulo
                )
            )
        }
    }

    fun actualizarDescripcion(descripcion: String) {
        descripcionUs = descripcion
        _uiState.update { estadoActual ->
            estadoActual.copy(
                tareaUIState = estadoActual.tareaUIState.copy(
                    descripcion = descripcion
                )
            )
        }
    }


    fun nuevaTarea() {

        if (tituloUs.isBlank()) {
            return
        }

        val nuevaTarea = Tarea(
            tituloUs,
            descripcionUs,
            tipoModulo,
            urgencia
        )

        if (urgencia) {
            _uiState.update { estadoActual ->
                estadoActual.copy(
                    tareasUrgentes = estadoActual.tareasUrgentes + nuevaTarea
                )
            }
        } else {
            _uiState.update { estadoActual ->
                estadoActual.copy(
                    tareas = estadoActual.tareas + nuevaTarea
                )
            }
        }

        tituloUs = ""
        descripcionUs = ""
        tipoModulo = ""
        urgencia = false

        _uiState.update { estadoActual ->
            estadoActual.copy(
                tareaUIState = estadoActual.tareaUIState.copy(
                    tituloTarea = "",
                    descripcion = "",
                    modulos = "",
                    esUrgente = false
                )
            )
        }

    }


}