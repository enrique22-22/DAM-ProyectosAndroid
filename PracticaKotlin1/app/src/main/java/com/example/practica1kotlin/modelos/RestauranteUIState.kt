package com.example.practica1kotlin.modelos

data class RestauranteUIState (
    val pedidos: MutableList<Pedido> = mutableListOf()
)