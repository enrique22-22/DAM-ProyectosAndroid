package com.example.practica2_examenkotlin.modelos

import com.example.practica2_examenkotlin.R

data class Moto (
    val matricula: String,
    val cilindrada: String,
    val precioBase: Double
) : Vehiculo (
    "Moto",
    imagen = R.drawable.icons8_moto_94
)