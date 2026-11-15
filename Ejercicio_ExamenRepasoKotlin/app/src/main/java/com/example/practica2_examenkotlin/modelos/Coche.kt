package com.example.practica2_examenkotlin.modelos

import com.example.practica2_examenkotlin.R

data class Coche (
    val matricula: String,
    val tipoMotor: String,
    val precioBase: Double
) : Vehiculo (
    "Coche",
    imagen = R.drawable.icons8_car_100
)