package com.example.practica2_examenkotlin.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.practica2_examenkotlin.modelos.Coche
import com.example.practica2_examenkotlin.modelos.Furgoneta
import com.example.practica2_examenkotlin.modelos.Moto
import com.example.practica2_examenkotlin.modelos.OrdenReparacion
import com.example.practica2_examenkotlin.modelos.TallerUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TallerViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TallerUIState())

    val uiState : StateFlow<TallerUIState> = _uiState.asStateFlow()

    var vehiculoSeleccionado by mutableStateOf("Coche")
        private set
    var tipoMatricula by mutableStateOf("")
        private set

    var tipoMotor by mutableStateOf("Gasolina")
        private set

    var tipoCilindrada by mutableStateOf("125cc")
        private set

    var tipoEsRefrigerada by mutableStateOf(false)
        private set

    var mostrarAlertaError by mutableStateOf(false)
        private set

    var mensajeError by mutableStateOf("")
        private set

    var matriculaIncorrecta by mutableStateOf(false)
        private set
    var mostrarAlertaExito by mutableStateOf(false)
        private set
    var horasManoObra by mutableStateOf("")
        private set

    @RequiresApi(Build.VERSION_CODES.O)
    val fecha: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

    var tipoUrgenciaReparacion by mutableStateOf(false)
        private set

    var totalCarrito by mutableStateOf(0.0)
        private set

    fun cerrarAlertaError() {
        mostrarAlertaError = false
        _uiState.update { estadoActual ->
            estadoActual.copy(mostrarAlertaError = false)
        }
    }

    fun cerrarAlertaExito() {
        mostrarAlertaExito = false
        _uiState.update { estadoActual ->
            estadoActual.copy(mostrarAlertaExito = false)
        }
    }

    fun seleccionarVehiculo(vehiculo: String) {
        vehiculoSeleccionado = vehiculo
        val nuevoPrecio = obtenerPrecioHoras()
        _uiState.update { estadoActual ->
            estadoActual.copy(
                vehiculoSeleccionado = vehiculo,
                precioHora = nuevoPrecio
            )
        }
    }

    fun actualizarTipoMatricula(matricula: String) {
        val enMayusculas = matricula.uppercase()
        val hayError = !Regex("^[0-9]{0,4}[A-Z]{0,3}$").matches(enMayusculas)

        tipoMatricula = enMayusculas
        matriculaIncorrecta = hayError

        if (matricula.length <= 7) {
            _uiState.update { estadoActual ->
                estadoActual.copy(
                    tipoMatricula = enMayusculas,
                    matriculaIncorrecta = hayError
                )
            }
        }
    }

    fun actualizarTipoMotor(motor: String) {
        tipoMotor = motor
        val nuevoPrecio = obtenerPrecioHoras()
        _uiState.update { estadoActual ->
            estadoActual.copy(
                tipoMotor = motor,
                precioHora = nuevoPrecio
            )
        }
    }

    fun actualizartipoCilindrada(cilindrada: String) {
        tipoCilindrada = cilindrada
        val nuevoPrecio = obtenerPrecioHoras()
        _uiState.update { estadoActual ->
            estadoActual.copy(
                tipoCilindrada = cilindrada,
                precioHora = nuevoPrecio
            )
        }
    }

    fun actualizarTipoEsRefrigerada(refrigerada: Boolean) {
        tipoEsRefrigerada = refrigerada
        val nuevoPrecio = obtenerPrecioHoras()
        _uiState.update { estadoActual ->
            estadoActual.copy(
                tipoEsRefrigerada = refrigerada,
                precioHora = nuevoPrecio
            )
        }
    }

    fun actualizarManoDeObra(manoObra: String) {
        if (manoObra.all { it.isDigit() }) {
            horasManoObra = manoObra
            _uiState.update { estadoActual ->
                estadoActual.copy(horasManoObra = manoObra)
            }
        }
    }

    fun actualizarUrgencia(urgente: Boolean) {
        tipoUrgenciaReparacion = urgente
        _uiState.update { estadoActual ->
            estadoActual.copy(tipoUrgenciaReparacion = urgente)
        }
    }

    fun obtenerPrecioHoras() : Double {
        return if (vehiculoSeleccionado == "Coche") {
            if (tipoMotor == "Gasolina") {
                40.0
            } else if (tipoMotor == "Diesel" ) {
                45.0
            } else {
                55.0
            }
        } else if (vehiculoSeleccionado == "Moto") {
            if (tipoCilindrada == "125cc") {
                30.0
            } else {
                40.0
            }
        } else {
            if (tipoEsRefrigerada) {
                65.0
            } else {
                50.0
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun registrarOrden() {
        val matriculaLimpia = _uiState.value.tipoMatricula.trim()
        val patronMatricula = Regex("^[0-9]{4}[A-Z]{3}$")
        if (!patronMatricula.matches(tipoMatricula)) {
            mensajeError = "La matrícula debe tener exactamente 4 números y 3 letras mayúsculas (ej. 1234ABC)."
            mostrarAlertaError = true
            _uiState.update { estadoActual ->
                estadoActual.copy(
                    mensajeError = mensajeError,
                    mostrarAlertaError = true
                )
            }
            return
        }

        val horas = horasManoObra.toIntOrNull()
        if (horas == null || horas <= 0) {
            mensajeError = "Debes introducir un número de horas mayor que 0."
            mostrarAlertaError = true
            _uiState.update { estadoActual ->
                estadoActual.copy(
                    mensajeError = mensajeError,
                    mostrarAlertaError = true
                )
            }
            return
        }

        val precioHoras = obtenerPrecioHoras()

        val vehiculoReal = if (vehiculoSeleccionado == "Coche") {
            Coche(
                matricula = tipoMatricula,
                tipoMotor = tipoMotor,
                precioBase = precioHoras
            )
        } else if (vehiculoSeleccionado == "Moto") {
            Moto(
                matricula = tipoMatricula,
                cilindrada = tipoCilindrada,
                precioBase = precioHoras
            )
        } else {
            Furgoneta(
                matricula = tipoMatricula,
                esRefrigerada = tipoEsRefrigerada,
                precioBase = precioHoras
            )
        }

        val nuevaOrden = OrdenReparacion (
            vehiculo = vehiculoReal,
            horasManoObra = horasManoObra,
            esUrgente = tipoUrgenciaReparacion,
            fechaEntrada = fecha
        )

        val costePorOrden = if (tipoUrgenciaReparacion) {
            (precioHoras * horas) + 20.0
        } else {
            precioHoras * horas
        }

        totalCarrito += costePorOrden

        if (tipoUrgenciaReparacion) {
            _uiState.update { estadoActual ->
                estadoActual.copy(
                    ordenUrgentes = estadoActual.ordenUrgentes + nuevaOrden
                )
            }
        } else {
            _uiState.update { estadoActual ->
                estadoActual.copy(
                    ordenNormales = estadoActual.ordenNormales + nuevaOrden
                )
            }
        }

        _uiState.update { estadoActual ->
            estadoActual.copy(
                tipoMatricula = "",
                horasManoObra = "",
                totalCarrito = totalCarrito,
                mostrarAlertaExito = true
            )
        }

    }

    fun limpiarFormulario() {

    }
}