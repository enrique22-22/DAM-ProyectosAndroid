package com.example.practica_examen_enrique.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.practica_examen_enrique.modelos.CineUIState
import com.example.practica_examen_enrique.modelos.Entrada
import com.example.practica_examen_enrique.modelos.Pedido
import com.example.practica_examen_enrique.modelos.Producto
import com.example.practica_examen_enrique.modelos.Snack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CineViewModel : ViewModel() {
    private  val _uiState = MutableStateFlow(CineUIState())

    val uiState : StateFlow<CineUIState> = _uiState.asStateFlow()

    var productoSeleccionado by mutableStateOf("Entrada")
        private set

    var tipoPelicula by mutableStateOf("Avengers")
        private set

    var TipoPrecio by mutableStateOf("")
        private set

    var tipoButaca by mutableStateOf("Normal")
        private set

    var tamanyoSnack by mutableStateOf("Pequeño")
        private set

    var cantidad by mutableStateOf("")
        private set

    var totalCarrito by mutableStateOf(0.0)
        private set
    val fecha: String = "2026-10-27"

    fun seleccionarProducto(producto: String) {
        productoSeleccionado = producto
    }

    fun actualizarTipoPelicula(pelicula: String) {
        tipoPelicula = pelicula
    }

    fun actualizarPrecio(precio: String) {
        TipoPrecio = precio
    }

    fun actualizarTipoButaca(butaca: String) {
        tipoButaca = butaca
    }

    fun actualizarTamanyoSnack(tamanyo: String) {
        tamanyoSnack = tamanyo
    }

    fun actualizarCantidad(nuevaCantidad: String) {
        if (nuevaCantidad.all { it.isDigit() }) {
            cantidad = nuevaCantidad
        }
    }

    fun obtenerPrecioUnitario(): Double {
        return if (productoSeleccionado == "Entrada") {
            if (tipoButaca == "Premium") 12.0 else 8.0
        } else {
            if (tamanyoSnack == "Pequeño") {
                3.0
            } else if (tamanyoSnack == "Mediano") {
                5.0
            } else {
                7.0
            }
        }
    }

    fun agregarPedido() {
        val cant = cantidad.toIntOrNull()
        if (cant == null || cant <= 0) return

        val precioUnitario = obtenerPrecioUnitario()

        val productoReal = if (productoSeleccionado == "Entrada") {
            Entrada (
                precio = precioUnitario.toString(),
                pelicula = tipoPelicula,
                butaca = tipoButaca
            )
        } else {
            Snack (
                precio = precioUnitario.toString(),
                tamanyo = tamanyoSnack
            )
        }

        val nuevoPedido = Pedido (
            producto = productoReal,
            cantidad = cantidad,
            fecha = fecha
        )

        totalCarrito += precioUnitario * cant

        _uiState.update { estadoActual ->
            val listaActualizada = estadoActual.pedidos.toMutableList()
            listaActualizada.add(nuevoPedido)
            estadoActual.copy(pedidos = listaActualizada)
        }

        cantidad = ""
    }
}