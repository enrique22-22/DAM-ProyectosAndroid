package com.example.practica_examen_enrique.modelos

data class Entrada (
    val pelicula: String = "",
    val butaca: String = "",
    val precio: String = ""
) : Producto (
    "Entrada"
)