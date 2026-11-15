package com.example.practica_examen_enrique

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practica_examen_enrique.modelos.Bebida
import com.example.practica_examen_enrique.modelos.Entrada
import com.example.practica_examen_enrique.modelos.Pedido
import com.example.practica_examen_enrique.modelos.Snack
import com.example.practica_examen_enrique.ui.CineViewModel

@RequiresApi(Build.VERSION_CODES.O)
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

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { cineViewModel.seleccionarProducto("Bebida") }
                ) {
                    Text(
                        text = "BEBIDA",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = cineViewModel.productoSeleccionado == "Bebida",
                            onClick = { cineViewModel.seleccionarProducto("Bebida") }
                        )
                        Text(text = "Bebida")
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
        } else if (cineViewModel.productoSeleccionado == "Snack") {
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
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Sabor:")
                FilterChip(
                    selected = cineViewModel.saborBebida == "Cola",
                    onClick = { cineViewModel.actualizarSaborBebida("Cola") },
                    label = { Text("Cola") }
                )
                FilterChip(
                    selected = cineViewModel.saborBebida == "Naranja",
                    onClick = { cineViewModel.actualizarSaborBebida("Naranja") },
                    label = { Text("Naranja") }
                )
                FilterChip(
                    selected = cineViewModel.saborBebida == "Agua",
                    onClick = { cineViewModel.actualizarSaborBebida("Agua") },
                    label = { Text("Agua") }
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(text = "¿Con hielo?")
                Switch(
                    checked = cineViewModel.conHielo,
                    onCheckedChange = { cineViewModel.actualizarConHielo(it) }
                )
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
            text = "Precio unidad: ${cineViewModel.calcularPrecioUnitario()} €",
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
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.pedidos) { pedido ->
                ItemPedido(pedido = pedido)
            }
        }
    }
}

@Composable
fun ItemPedido(pedido: Pedido) {
    var abierto by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    val tituloProducto = if (pedido.producto is Entrada) {
                        "Entrada - ${pedido.producto.pelicula}"
                    } else if (pedido.producto is Snack) {
                        "Snack - ${pedido.producto.tamanyo}"
                    } else if (pedido.producto is Bebida) {
                        "Bebida - ${pedido.producto.sabor}"
                    } else {
                        pedido.producto.tipoProducto
                    }

                    Text(
                        text = tituloProducto,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Cantidad: ${pedido.cantidad} | Fecha: ${pedido.fecha}",
                        fontSize = 13.sp
                    )
                }

                /*
                TextButton(onClick = { abierto = !abierto }) {
                    Text(text = if (abierto) "Ocultar ▲" else "Detalles ▼")
                }
                 */

                IconButton(onClick = { abierto = !abierto }) {
                    Icon(
                        painter = if (abierto) {
                            painterResource(R.drawable.ic_launcher_foreground)
                        } else {
                            painterResource(R.drawable.ic_launcher_background)
                        },
                        contentDescription = if (abierto) "Cerrar" else "Abrir"
                    )
                }
            }

            if (abierto) {
                Spacer(modifier = Modifier.height(6.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(6.dp))

                val detalleProducto = if (pedido.producto is Entrada) {
                    val e = pedido.producto
                    "Película: ${e.pelicula} | Butaca: ${e.butaca} | Precio ud: ${e.precio} €"
                } else if (pedido.producto is Snack) {
                    val s = pedido.producto
                    "Tamaño: ${s.tamanyo} | Precio ud: ${s.precio} €"
                } else if (pedido.producto is Bebida) {
                    val b = pedido.producto
                    val hieloTexto = if (b.conHielo) "Con hielo" else "Sin hielo"
                    "Sabor: ${b.sabor} | $hieloTexto | Precio ud: ${b.precio} €"
                } else {
                    ""
                }

                Text(
                    text = detalleProducto,
                    fontSize = 14.sp
                )
            }
        }
    }
}