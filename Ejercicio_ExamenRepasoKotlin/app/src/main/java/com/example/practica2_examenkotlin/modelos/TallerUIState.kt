package com.example.practica2_examenkotlin.modelos

data class TallerUIState (
    val vehiculoSeleccionado: String = "Coche",
    val tipoMatricula: String = "",
    val tipoMotor: String = "Gasolina",
    val tipoCilindrada: String = "125cc",
    val tipoEsRefrigerada: Boolean = false,
    val horasManoObra: String = "",
    val tipoUrgenciaReparacion: Boolean = false,
    val precioHora: Double = 40.0,
    val totalCarrito: Double = 0.0,
    val matriculaIncorrecta: Boolean = false,
    val mostrarAlertaError: Boolean = false,
    val mensajeError: String = "",
    val mostrarAlertaExito: Boolean = false,
    val ordenNormales: List<OrdenReparacion> = listOf(),
    val ordenUrgentes: List<OrdenReparacion> = listOf()
)