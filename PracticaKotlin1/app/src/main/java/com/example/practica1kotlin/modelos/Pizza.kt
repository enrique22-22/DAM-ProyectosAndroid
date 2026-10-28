package com.example.practica1kotlin.modelos

import com.example.practica1kotlin.R


data class Pizza (
    val tamanyo: String = "",
    val tipoPizza: String = ""
) : Producto (
    "Pizza",
    R.drawable.pizza
)