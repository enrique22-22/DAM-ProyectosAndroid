package com.example.practica_examen_enrique.modelos

data class Pedido (
    val producto: Producto,
    val cantidad: String = "",
    val fecha: String = ""
)