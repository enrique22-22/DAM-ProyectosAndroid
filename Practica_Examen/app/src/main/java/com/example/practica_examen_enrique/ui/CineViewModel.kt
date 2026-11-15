package com.example.practica_examen_enrique.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.practica_examen_enrique.modelos.Bebida
import com.example.practica_examen_enrique.modelos.CineUIState
import com.example.practica_examen_enrique.modelos.Entrada
import com.example.practica_examen_enrique.modelos.Pedido
import com.example.practica_examen_enrique.modelos.Producto
import com.example.practica_examen_enrique.modelos.Snack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CineViewModel : ViewModel() {
    private  val _uiState = MutableStateFlow(CineUIState())

    val uiState : StateFlow<CineUIState> = _uiState.asStateFlow()

    var productoSeleccionado by mutableStateOf("Entrada")
        private set

    var tipoPelicula by mutableStateOf("Avengers")
        private set
    var saborBebida by mutableStateOf("Cola")
        private set
    var tipoButaca by mutableStateOf("Normal")
        private set

    var conHielo by mutableStateOf(false)
        private set
    var tamanyoSnack by mutableStateOf("Pequeño")
        private set

    var cantidad by mutableStateOf("")
        private set

    var totalCarrito by mutableStateOf(0.0)
        private set
    @RequiresApi(Build.VERSION_CODES.O)
    val fecha: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

    fun seleccionarProducto(producto: String) {
        productoSeleccionado = producto
    }

    fun actualizarTipoPelicula(pelicula: String) {
        tipoPelicula = pelicula
    }

    fun actualizarTipoButaca(butaca: String) {
        tipoButaca = butaca
    }

    fun actualizarTamanyoSnack(tamanyo: String) {
        tamanyoSnack = tamanyo
    }

    fun actualizarSaborBebida(sabor: String) {
        saborBebida = sabor
    }

    fun actualizarConHielo(hielo: Boolean) {
        conHielo = hielo
    }

    fun actualizarCantidad(nuevaCantidad: String) {
        if (nuevaCantidad.all { it.isDigit() }) {
            cantidad = nuevaCantidad
        }
    }

    fun calcularPrecioUnitario(): Double {
        return if (productoSeleccionado == "Entrada") {
            if (tipoButaca == "Premium") 12.0 else 8.0
        } else if (productoSeleccionado == "Snack") {
            if (tamanyoSnack == "Pequeño") {
                3.0
            } else if (tamanyoSnack == "Mediano") {
                5.0
            } else {
                7.0
            }
        } else {
            if (conHielo) 3.0 else 2.5
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun agregarPedido() {
        val cant = cantidad.toIntOrNull()
        if (cant == null || cant <= 0) return

        val precioUnitario = calcularPrecioUnitario()

        val productoReal = if (productoSeleccionado == "Entrada") {
            Entrada (
                precio = precioUnitario.toString(),
                pelicula = tipoPelicula,
                butaca = tipoButaca
            )
        } else if (productoSeleccionado == "Snack") {
            Snack(
                precio = precioUnitario.toString(),
                tamanyo = tamanyoSnack
            )
        } else {
            Bebida(
                sabor = saborBebida,
                conHielo = conHielo,
                precio = precioUnitario.toString()
            )
        }

        val nuevoPedido = Pedido (
            producto = productoReal,
            cantidad = cantidad,
            fecha = fecha
        )

        totalCarrito += precioUnitario * cant

        _uiState.update { estadoActual ->
            estadoActual.copy(
                pedidos = estadoActual.pedidos + nuevoPedido
            )
        }

        cantidad = ""
    }
}