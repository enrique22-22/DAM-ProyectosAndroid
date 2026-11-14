package com.example.practica_examen_enrique

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practica_examen_enrique.modelos.Entrada
import com.example.practica_examen_enrique.modelos.Snack
import com.example.practica_examen_enrique.ui.CineViewModel

@Composable
fun PantallaCine (
    modifier: Modifier = Modifier,
    cineViewModel: CineViewModel = viewModel()
) {
    val uiState by cineViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "CINE DAM",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.purple_200)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Elige el producto", fontSize = 20.sp, color = colorResource(R.color.purple_200))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { cineViewModel.seleccionarProducto("Entrada") }
                ) {
                    Text(
                        text = "ENTRADA",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = cineViewModel.productoSeleccionado == "Entrada",
                            onClick = { cineViewModel.seleccionarProducto("Entrada") }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Entrada")
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { cineViewModel.seleccionarProducto("Snack") }
                ) {
                    Text(
                        text = "SNACK",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = cineViewModel.productoSeleccionado == "Snack",
                            onClick = { cineViewModel.seleccionarProducto("Snack") }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Snack")
                    }
                }
            }
        }


        if (cineViewModel.productoSeleccionado == "Entrada") {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Película:")
                FilterChip(
                    selected = cineViewModel.tipoPelicula == "Avengers",
                    onClick = { cineViewModel.actualizarTipoPelicula("Avengers") },
                    label = { Text("Avengers") }
                )
                FilterChip(
                    selected = cineViewModel.tipoPelicula == "FastFurius",
                    onClick = { cineViewModel.actualizarTipoPelicula("FastFurius") },
                    label = { Text("FastFurius") }
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Butaca:")
                FilterChip(
                    selected = cineViewModel.tipoButaca == "Premium",
                    onClick = { cineViewModel.actualizarTipoButaca("Premium") },
                    label = { Text("Premium") }
                )
                FilterChip(
                    selected = cineViewModel.tipoButaca == "Normal",
                    onClick = { cineViewModel.actualizarTipoButaca("Normal") },
                    label = { Text("Normal") }
                )
            }
        } else {
            Text(text = "Tamaño del Snack:")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = cineViewModel.tamanyoSnack == "Pequeño",
                        onCheckedChange = { checked ->
                            if (checked) cineViewModel.actualizarTamanyoSnack("Pequeño")
                        }
                    )
                    Text(text = "Pequeña")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = cineViewModel.tamanyoSnack == "Mediano",
                        onCheckedChange = { checked ->
                            if (checked) cineViewModel.actualizarTamanyoSnack("Mediano")
                        }
                    )
                    Text(text = "Mediana")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = cineViewModel.tamanyoSnack == "Grande",
                        onCheckedChange = { checked ->
                            if (checked) cineViewModel.actualizarTamanyoSnack("Grande")
                        }
                    )
                    Text(text = "Grande")
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = cineViewModel.cantidad,
                onValueChange = { cineViewModel.actualizarCantidad(it) },
                label = { Text("Cantidad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = cineViewModel.fecha,
                onValueChange = {},
                enabled = false,
                label = { Text("Fecha") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Precio unidad: ${cineViewModel.obtenerPrecioUnitario()} €",
            fontWeight = FontWeight.Medium
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { cineViewModel.agregarPedido() }) {
                Text(text = "Añadir")
            }
            Button(onClick = { }) {
                Text(text = "Cancelar")
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 15.dp))

        Text(
            text = "Carrito: ${cineViewModel.totalCarrito} €",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.pedidos) { pedido ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        val descripcionProducto = if (pedido.producto is Entrada) {
                            val h = pedido.producto
                            "Entrada (${h.pelicula}, ${h.butaca}) - ${h.precio}€"
                        } else if (pedido.producto is Snack) {
                            val p = pedido.producto
                            "Snack (${p.tamanyo} - ${p.precio}€"
                        } else {
                            pedido.producto.tipoProducto
                        }

                        Text(
                            text = descripcionProducto,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Cantidad: ${pedido.cantidad} | Fecha: ${pedido.fecha}"
                        )
                    }
                }
            }
        }
    }
}