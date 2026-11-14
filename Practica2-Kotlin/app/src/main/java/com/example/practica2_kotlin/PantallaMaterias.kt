package com.example.practica2_kotlin

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practica2_kotlin.modelo.Tarea
import com.example.practica2_kotlin.ui.MateriasViewModel

@Composable
fun PantallaModulos(
    modifier: Modifier = Modifier,
    materiasViewModel: MateriasViewModel = viewModel()
) {
    val uiState by materiasViewModel.uiState.collectAsState()

    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Modulos del Alumnado",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.teal_700)
        )

        OutlinedTextField(
            value = uiState.tareaUIState.tituloTarea,
            onValueChange = { nuevoTexto -> materiasViewModel.actualizarTitulo(nuevoTexto) },
            label = { Text("Título de la tarea") },
            modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
            singleLine = true
        )

        OutlinedTextField(
            value = uiState.tareaUIState.descripcion,
            onValueChange = { nuevaDescripcion -> materiasViewModel.actualizarDescripcion(nuevaDescripcion) },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Módulo:",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            FilterChip(
                selected = (uiState.tareaUIState.modulos == "AD"),
                onClick = { materiasViewModel.actualizarModulo("AD") },
                label = { Text("AD") }
            )
            FilterChip(
                selected = (uiState.tareaUIState.modulos  == "DI"),
                onClick = { materiasViewModel.actualizarModulo("DI") },
                label = { Text("DI") }
            )
            FilterChip(
                selected = (uiState.tareaUIState.modulos  == "PMDM"),
                onClick = { materiasViewModel.actualizarModulo("PMDM") },
                label = { Text("PMDM") }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "¿Es urgente?",
                fontWeight = FontWeight.Bold
            )

            Checkbox(
                checked = uiState.tareaUIState.esUrgente,
                onCheckedChange = { estaMarcado ->
                    materiasViewModel.actualizarEleccionUrgencia(estaMarcado)
                }
            )
        }

        Button(
            onClick = {
                materiasViewModel.nuevaTarea()
                focusRequester.requestFocus()},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Añadir Tarea")
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.outline
        )

        Text(
            text = "Tareas Guardadas:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

              LazyColumn(
                  modifier = Modifier.weight(0.5F),
              ) {
                  items(uiState.tareas) { tarea ->
                      ItemTarea(tarea = tarea)
                  }
              }

            LazyColumn(
                modifier = Modifier.weight(0.5F),
            ) {

                items(uiState.tareasUrgentes) { tarea ->
                    ItemTarea(tarea = tarea)
                }
            }
        }

    }
}


@Composable
fun ItemTarea(tarea: Tarea) {
    val colorTarjeta = if (tarea.esUrgente) {
        MaterialTheme.colorScheme.errorContainer
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colorTarjeta)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Título: ${tarea.tituloTarea}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Modulo elegido: ${tarea.modulos}",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = "Descripción: ${tarea.descripcion}")

                if (tarea.esUrgente) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Tarea Urgente",
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }

        }
    }
}