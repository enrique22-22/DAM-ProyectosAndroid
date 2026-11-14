package com.example.practica2_kotlin.modelo

data class Tarea (
    var tituloTarea: String = "",
    var descripcion: String = "",
    var modulos: String = "",
    var esUrgente: Boolean = false
)