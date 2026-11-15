package com.example.practica2_examenkotlin.modelos

import com.example.practica2_examenkotlin.R

data class Furgoneta (
    val matricula: String,
    val esRefrigerada: Boolean,
    val precioBase: Double
) : Vehiculo (
    "Furgoneta",
    imagen = R.drawable.icons8_van_60
)