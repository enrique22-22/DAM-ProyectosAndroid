package com.example.practica2_examen2_kotlin.ui

import androidx.lifecycle.ViewModel
import com.example.practica2_examen2_kotlin.modelos.InstitutoUIState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.practica2_examen2_kotlin.modelos.Alumno
import com.example.practica2_examen2_kotlin.modelos.Persona
import com.example.practica2_examen2_kotlin.modelos.Profesor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InstitutoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(InstitutoUIState())
    val uiState: StateFlow<InstitutoUIState> = _uiState.asStateFlow()

    var nombre by mutableStateOf("")
        private set

    var rolSeleccionado by mutableStateOf("Profesor")
        private set

    var nia by mutableStateOf("")
        private set

    var niaConError by mutableStateOf(false)
        private set

    var cursoAlumno by mutableStateOf("Primero")
        private set

    var esTutor by mutableStateOf(false)
        private set

    var impartePrimero by mutableStateOf(false)
        private set

    var imparteSegundo by mutableStateOf(false)
        private set

    var codigoGenerado by mutableStateOf("")
        private set

    fun actualizarNombre(nuevoNombre: String) {
        nombre = nuevoNombre
        val nuevoCodigo = calcularCodigo()
        codigoGenerado = nuevoCodigo
        _uiState.update { estadoActual ->
            estadoActual.copy(
                nombre = nuevoNombre,
                codigoGenerado = nuevoCodigo
            )
        }
    }

    fun actualizarRol(nuevoRol: String) {
        rolSeleccionado = nuevoRol
        val nuevoCodigo = calcularCodigo()
        codigoGenerado = nuevoCodigo
        _uiState.update { estadoActual ->
            estadoActual.copy(
                rolSeleccionado = nuevoRol,
                codigoGenerado = nuevoCodigo
            )
        }
    }

    fun actualizarNia(nuevoNia: String) {
        // Solo acepta dígitos y máximo 5 caracteres
        if (nuevoNia.all { it.isDigit() } && nuevoNia.length <= 5) {
            nia = nuevoNia
            // Si escribe 00000 se marca como error para pintarlo en rojo
            val error = (nuevoNia == "00000")
            niaConError = error
            val nuevoCodigo = calcularCodigo()
            codigoGenerado = nuevoCodigo

            _uiState.update { estadoActual ->
                estadoActual.copy(
                    nia = nuevoNia,
                    niaConError = error,
                    codigoGenerado = nuevoCodigo
                )
            }
        }
    }

    fun actualizarCursoAlumno(curso: String) {
        cursoAlumno = curso
        _uiState.update { estadoActual ->
            estadoActual.copy(cursoAlumno = curso)
        }
    }

    fun actualizarEsTutor(tutor: Boolean) {
        esTutor = tutor
        val nuevoCodigo = calcularCodigo()
        codigoGenerado = nuevoCodigo
        _uiState.update { estadoActual ->
            estadoActual.copy(
                esTutor = tutor,
                codigoGenerado = nuevoCodigo
            )
        }
    }

    fun actualizarImpartePrimero(marcado: Boolean) {
        impartePrimero = marcado
        _uiState.update { estadoActual ->
            estadoActual.copy(impartePrimero = marcado)
        }
    }

    fun actualizarImparteSegundo(marcado: Boolean) {
        imparteSegundo = marcado
        _uiState.update { estadoActual ->
            estadoActual.copy(imparteSegundo = marcado)
        }
    }

    // Cálculo automático del código según el rol
    private fun calcularCodigo(): String {
        val nombreLimpio = nombre.trim()
        if (nombreLimpio.isEmpty()) return ""

        return if (rolSeleccionado == "Alumno") {
            // Solo si NIA tiene 5 dígitos y no es 00000
            if (nia.length == 5 && nia != "00000") {
                val ultimaLetra = nombreLimpio.last()
                // Posiciones impares (1ª, 3ª y 5ª -> índices 0, 2 y 4)
                val imparesNia = "${nia[0]}${nia[2]}${nia[4]}"
                "$ultimaLetra$imparesNia"
            } else {
                ""
            }
        } else {
            // Profesor: nombre invertido + SI o NO según sea tutor
            val invertido = nombreLimpio.reversed()
            val sufijo = if (esTutor) "SI" else "NO"
            "$invertido$sufijo"
        }
    }

    fun anadirPersona() {
        val nombreLimpio = nombre.trim()
        if (nombreLimpio.isEmpty()) return

        if (rolSeleccionado == "Alumno") {
            if (nia.length != 5 || nia == "00000") {
                niaConError = true
                _uiState.update { it.copy(niaConError = true) }
                return
            }
            val nuevoAlumno = Alumno(
                nombre = nombreLimpio,
                curso = cursoAlumno,
                nia = nia,
                codigo = calcularCodigo()
            )
            _uiState.update { estadoActual ->
                estadoActual.copy(
                    listaAlumnos = estadoActual.listaAlumnos + nuevoAlumno
                )
            }
        } else {
            val cursosSeleccionados = mutableListOf<String>()
            if (impartePrimero) cursosSeleccionados.add("Primero")
            if (imparteSegundo) cursosSeleccionados.add("Segundo")
            if (cursosSeleccionados.isEmpty()) return

            val nuevoProfesor = Profesor(
                nombre = nombreLimpio,
                cursos = cursosSeleccionados,
                esTutor = esTutor,
                codigo = calcularCodigo()
            )
            _uiState.update { estadoActual ->
                estadoActual.copy(
                    listaProfesores = estadoActual.listaProfesores + nuevoProfesor
                )
            }
        }

        limpiarFormulario()
    }

    fun limpiarFormulario() {
        nombre = ""
        rolSeleccionado = "Profesor"
        nia = ""
        niaConError = false
        cursoAlumno = "Primero"
        esTutor = false
        impartePrimero = false
        imparteSegundo = false
        codigoGenerado = ""

        _uiState.update { estadoActual ->
            estadoActual.copy(
                nombre = "",
                rolSeleccionado = "Profesor",
                nia = "",
                niaConError = false,
                cursoAlumno = "Primero",
                esTutor = false,
                impartePrimero = false,
                imparteSegundo = false,
                codigoGenerado = ""
            )
        }
    }
}