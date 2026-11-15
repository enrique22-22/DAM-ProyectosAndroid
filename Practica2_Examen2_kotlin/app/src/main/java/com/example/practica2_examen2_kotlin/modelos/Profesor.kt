package com.example.practica2_examen2_kotlin.modelos

import com.example.practica2_examen2_kotlin.R

data class Profesor(
    val nombre: String,
    val cursos: List<String>,
    val esTutor: Boolean,
    val codigo: String
) : Persona(
    rol = "Profesor",
    imagen = R.drawable.ic_launcher_foreground
)