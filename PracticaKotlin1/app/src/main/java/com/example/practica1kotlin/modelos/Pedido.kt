package com.example.practica1kotlin.modelos

data class Pedido (
    val producto: Producto,
    val cantidad: String = "",
    val fecha: String = "",
)