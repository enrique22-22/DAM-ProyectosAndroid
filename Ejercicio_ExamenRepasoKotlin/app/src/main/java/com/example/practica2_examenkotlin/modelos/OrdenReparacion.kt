package com.example.practica2_examenkotlin.modelos

data class OrdenReparacion (
    val vehiculo: Vehiculo,
    val horasManoObra: String,
    val esUrgente: Boolean,
    val fechaEntrada: String
)