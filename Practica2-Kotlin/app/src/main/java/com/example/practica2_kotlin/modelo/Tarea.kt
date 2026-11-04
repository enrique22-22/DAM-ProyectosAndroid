package com.example.practica2_kotlin.modelo

data class Tarea (
    val tituloTarea: String = "",
    val descripcion: String = "",
    val modulos: String = "",
    val esUrgente: Boolean = false
)