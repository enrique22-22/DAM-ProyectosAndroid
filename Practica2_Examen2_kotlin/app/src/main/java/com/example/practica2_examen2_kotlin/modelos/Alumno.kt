package com.example.practica2_examen2_kotlin.modelos

import com.example.practica2_examen2_kotlin.R

data class Alumno(
    val nombre: String,
    val curso: String,
    val nia: String,
    val codigo: String
) : Persona(
    rol = "Alumno",
    imagen = R.drawable.ic_launcher_foreground
)