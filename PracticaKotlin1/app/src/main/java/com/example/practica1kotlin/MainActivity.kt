package com.example.practica1kotlin

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practica1kotlin.modelos.Hamburgesa
import com.example.practica1kotlin.modelos.Pizza
import com.example.practica1kotlin.ui.RestauranteViewModel
import com.example.practica1kotlin.ui.theme.Practica1KotlinTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica1KotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaRestaurante(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun PantallaRestaurante(
    modifier: Modifier = Modifier,
    restauranteViewModel: RestauranteViewModel = viewModel()
) {

    val uiState by restauranteViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Restaurante DAM",
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
                    modifier = Modifier.clickable { restauranteViewModel.seleccionarProducto("Hamburguesa") }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hamburguesa),
                        contentDescription = "Hamburguesa",
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = restauranteViewModel.productoSeleccionado == "Hamburguesa",
                            onClick = { restauranteViewModel.seleccionarProducto("Hamburguesa") }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Hamburguesa")
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { restauranteViewModel.seleccionarProducto("Pizza") }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pizza),
                        contentDescription = "Pizza",
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = restauranteViewModel.productoSeleccionado == "Pizza",
                            onClick = { restauranteViewModel.seleccionarProducto("Pizza") }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Pizza")
                    }
                }
            }
        }

        if (restauranteViewModel.productoSeleccionado == "Hamburguesa") {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Carne:")
                FilterChip(
                    selected = restauranteViewModel.tipoCarne == "Pollo",
                    onClick = { restauranteViewModel.actualizarTipoCarne("Pollo") },
                    label = { Text("Pollo") }
                )
                FilterChip(
                    selected = restauranteViewModel.tipoCarne == "Ternera",
                    onClick = { restauranteViewModel.actualizarTipoCarne("Ternera") },
                    label = { Text("Ternera") }
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Queso:")
                Switch(
                    checked = restauranteViewModel.eleccionQueso == "Con queso",
                    onCheckedChange = { restauranteViewModel.actualizarEleccionQueso(it) }
                )
                Text(text = restauranteViewModel.eleccionQueso)
            }

        } else {
        Text(text = "Tipo de Pizza:")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = restauranteViewModel.tipoPizza == "Margarita",
                    onCheckedChange = { checked ->
                        if (checked) restauranteViewModel.actualizarTipoPizza("Margarita")
                    }
                )
                Text(text = "Margarita")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = restauranteViewModel.tipoPizza == "Romana",
                    onCheckedChange = { checked ->
                        if (checked) restauranteViewModel.actualizarTipoPizza("Romana")
                    }
                )
                Text(text = "Romana")
            }
        }

        Text(text = "Tamaño:")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = restauranteViewModel.tamanyoPizza == "Pequeña",
                    onCheckedChange = { checked ->
                        if (checked) restauranteViewModel.actualizarTamanyoPizza("Pequeña")
                    }
                )
                Text(text = "Pequeña")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = restauranteViewModel.tamanyoPizza == "Mediana",
                    onCheckedChange = { checked ->
                        if (checked) restauranteViewModel.actualizarTamanyoPizza("Mediana")
                    }
                )
                Text(text = "Mediana")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = restauranteViewModel.tamanyoPizza == "Grande",
                    onCheckedChange = { checked ->
                        if (checked) restauranteViewModel.actualizarTamanyoPizza("Grande")
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
                value = restauranteViewModel.cantidad,
                onValueChange = { restauranteViewModel.actualizarCantidad(it) },
                label = { Text("Cantidad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = restauranteViewModel.fecha,
                onValueChange = {},
                enabled = false,
                label = { Text("Fecha") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { restauranteViewModel.agregarPedido() }) {
                Text(text = "Añadir")
            }
            Button(onClick = { }) {
                Text(text = "Cancelar")
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 15.dp))

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
                        val descripcionProducto = if (pedido.producto is Hamburgesa) {
                            val h = pedido.producto
                            "Hamburguesa (${h.tipoCarne}, ${h.eleccionQueso})"
                        } else if (pedido.producto is Pizza) {
                            val p = pedido.producto
                            "Pizza (${p.tipoPizza}, ${p.tamanyo})"
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