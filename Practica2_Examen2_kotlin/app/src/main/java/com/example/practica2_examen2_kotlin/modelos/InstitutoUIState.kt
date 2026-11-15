package com.example.practica2_examen2_kotlin.modelos

data class InstitutoUIState(
    val nombre: String = "",
    val rolSeleccionado: String = "Profesor",
    val nia: String = "",
    val niaConError: Boolean = false,
    val cursoAlumno: String = "Primero",
    val esTutor: Boolean = false,
    val impartePrimero: Boolean = false,
    val imparteSegundo: Boolean = false,
    val codigoGenerado: String = "",
    val listaAlumnos: List<Alumno> = listOf(),
    val listaProfesores: List<Profesor> = listOf()
)