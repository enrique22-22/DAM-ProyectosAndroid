package com.example.practica_examen_enrique.modelos

data class Bebida(
    val sabor: String = "",
    val conHielo: Boolean = false,
    val precio: String = ""
) : Producto(
    "Bebida"
)