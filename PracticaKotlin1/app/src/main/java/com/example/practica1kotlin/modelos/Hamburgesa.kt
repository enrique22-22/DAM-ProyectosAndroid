package com.example.practica1kotlin.modelos

import com.example.practica1kotlin.R

data class Hamburgesa  (
    val tipoCarne: String = "",
    val eleccionQueso: String = ""
) : Producto (
    "Hamburgesa",
    R.drawable.hamburguesa
)
