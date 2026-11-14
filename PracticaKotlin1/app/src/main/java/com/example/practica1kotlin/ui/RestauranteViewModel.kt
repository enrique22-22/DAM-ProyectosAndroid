package com.example.practica1kotlin.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.practica1kotlin.modelos.Hamburgesa
import com.example.practica1kotlin.modelos.Pedido
import com.example.practica1kotlin.modelos.Pizza
import com.example.practica1kotlin.modelos.RestauranteUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RestauranteViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RestauranteUIState())
    val uiState: StateFlow<RestauranteUIState> = _uiState.asStateFlow()

    var productoSeleccionado by mutableStateOf("Hamburguesa")
        private set

    var tipoCarne by mutableStateOf("Pollo")
        private set

    var eleccionQueso by mutableStateOf("Con queso")
        private set

    var tamanyoPizza by mutableStateOf("Mediana")
        private set

    var tipoPizza by mutableStateOf("Margarita")
        private set

    var cantidad by mutableStateOf("")
        private set

    val fecha: String = "2026-10-27"

    fun seleccionarProducto(producto: String) {
        productoSeleccionado = producto
    }

    fun actualizarTipoCarne(carne: String) {
        tipoCarne = carne
    }

    fun actualizarEleccionQueso(conQueso: Boolean) {
        eleccionQueso = if (conQueso) "Con queso" else "Sin queso"
    }

    fun actualizarTamanyoPizza(tamanyo: String) {
        tamanyoPizza = tamanyo
    }

    fun actualizarTipoPizza(tipo: String) {
        tipoPizza = tipo
    }

    fun actualizarCantidad(nuevaCantidad: String) {
        if (nuevaCantidad.all { it.isDigit() }) {
            cantidad = nuevaCantidad
        }
    }

    fun agregarPedido() {
        if (cantidad.isBlank()) return

        val productoReal = if (productoSeleccionado == "Hamburguesa") {
            Hamburgesa(
                tipoCarne = tipoCarne,
                eleccionQueso = eleccionQueso
            )
        } else {
            Pizza(
                tamanyo = tamanyoPizza,
                tipoPizza = tipoPizza
            )
        }

        val nuevoPedido = Pedido(
            producto = productoReal,
            cantidad = cantidad,
            fecha = fecha
        )

        _uiState.update { estadoActual ->
            val listaActualizada = estadoActual.pedidos.toMutableList()
            listaActualizada.add(nuevoPedido)
            estadoActual.copy(pedidos = listaActualizada)
        }

        cantidad = ""
    }
}