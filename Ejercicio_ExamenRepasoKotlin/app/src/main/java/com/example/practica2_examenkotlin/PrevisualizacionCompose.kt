package com.example.practica2_examenkotlin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ============================================================================
// PREVIEW 1: TEXTOS Y ESTILOS
// ============================================================================
@Preview(showBackground = true, name = "1. Textos y Estilos")
@Composable
fun PreviewTextos() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Desde strings.xml: ${stringResource(id = R.string.app_name)}")
        Text(text = "Color predefinido (Color.Blue)", color = Color.Blue)
        Text(text = "Color hexadecimal (0xFF...)", color = Color(0xFF6200EE))
        Text(text = "Color desde colors.xml", color = colorResource(id = R.color.teal_700))
        Text(
            text = "Color del tema MaterialTheme",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Tamaño 24.sp + Cursiva + Negrita",
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Texto centrado con TextAlign.Center y altura de línea 30.sp",
            textAlign = TextAlign.Center,
            lineHeight = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF0F0F0))
                .padding(8.dp)
        )
    }
}

// ============================================================================
// PREVIEW 2: CAMPOS DE TEXTO (TextField y OutlinedTextField)
// ============================================================================
@Preview(showBackground = true, name = "2. Campos de Texto")
@Composable
fun PreviewCamposTexto() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // TextField relleno
        TextField(
            value = "Texto relleno",
            onValueChange = {},
            label = { Text("TextField estándar") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // OutlinedTextField numérico con estilo
        OutlinedTextField(
            value = "12",
            onValueChange = {},
            label = { Text("Horas (Numérico)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            modifier = Modifier.fillMaxWidth()
        )

        // OutlinedTextField marcando error en rojo
        OutlinedTextField(
            value = "12345AB",
            onValueChange = {},
            label = { Text("Matrícula con error (isError = true)") },
            isError = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Red,
                unfocusedTextColor = Color.Red
            ),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // OutlinedTextField deshabilitado (solo lectura)
        OutlinedTextField(
            value = "15/11/2026",
            onValueChange = {},
            enabled = false,
            label = { Text("Fecha (enabled = false)") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// ============================================================================
// PREVIEW 3: IMÁGENES, ESCALADOS, FORMAS Y OPACIDAD
// ============================================================================
@Preview(showBackground = true, name = "3. Imágenes y Modificadores")
@Composable
fun PreviewImagenes() {
    val img = painterResource(id = R.drawable.ic_launcher_foreground)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Fila con diferentes formas y bordes
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar circular con borde
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = img,
                    contentDescription = "Circular",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF3F51B5))
                        .border(2.dp, Color.Red, CircleShape)
                )
                Text("CircleShape", fontSize = 12.sp)
            }

            // Esquinas redondeadas
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = img,
                    contentDescription = "Redondeada",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF009688))
                        .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
                )
                Text("RoundedCorner", fontSize = 12.sp)
            }

            // Con opacidad alpha = 0.4f
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = img,
                    contentDescription = "Opacidad",
                    alpha = 0.4f,
                    modifier = Modifier
                        .size(70.dp)
                        .background(Color.LightGray)
                )
                Text("alpha = 0.4f", fontSize = 12.sp)
            }
        }

        // Imagen ancha con ContentScale.Crop
        Image(
            painter = img,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color(0xFFE0E0E0))
        )
    }
}

// ============================================================================
// PREVIEW 4: LAYOUTS (Column, Row, Box y Weight)
// ============================================================================
@Preview(showBackground = true, name = "4. Layouts (Column, Row, Box)")
@Composable
fun PreviewLayouts() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // ROW con SpaceBetween
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE3F2FD))
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Start")
            Text("Center")
            Text("End")
        }

        // ANIDACIÓN ROW + COLUMN + WEIGHT (Perfil de usuario)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE8F5E9))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Usuario",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            ) {
                Text("Usuario", fontWeight = FontWeight.Bold)
                Text("Alumno 2º DAM", fontSize = 13.sp)
            }
            Button(onClick = {}) {
                Text("Perfil")
            }
        }

        // BOX (Superposición de elementos en capas)
        Box(
            modifier = Modifier
                .size(110.dp)
                .background(Color(0xFFCFD8DC))
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Check",
                tint = Color(0xFF2E7D32),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(4.dp)
            )
            Text(
                text = "BOX",
                color = Color.White,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(Color.Red)
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }

        // COLUMNAS REPARTIDAS AL 50% (weight(0.5f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Card(
                modifier = Modifier.weight(0.5f),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Text("Columna 50% Izq", modifier = Modifier.padding(12.dp), fontWeight = FontWeight.Bold)
            }
            Card(
                modifier = Modifier.weight(0.5f),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Text("Columna 50% Der", modifier = Modifier.padding(12.dp), fontWeight = FontWeight.Bold)
            }
        }
    }
}

// ============================================================================
// PREVIEW 5: BOTONES, CHIPS, RADIO, CHECKBOX, SWITCH Y CARD
// ============================================================================
@Preview(showBackground = true, name = "5. Botones y Controles")
@Composable
fun PreviewControles() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Botones
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {}) { Text("Button") }
            OutlinedButton(onClick = {}) { Text("Outlined") }
            TextButton(onClick = {}) { Text("Text") }
            IconButton(onClick = {}) {
                Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null, tint = Color.Red)
            }
        }

        // RadioButtons
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = true, onClick = {})
            Text("Coche")
            Spacer(modifier = Modifier.width(12.dp))
            RadioButton(selected = false, onClick = {})
            Text("Moto")
        }

        // Checkbox y Switch
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = true, onCheckedChange = {})
                Text("Urgente")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Refrigerada")
                Spacer(modifier = Modifier.width(8.dp))
                Switch(checked = true, onCheckedChange = {})
            }
        }

        // FilterChips
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = true, onClick = {}, label = { Text("Gasolina") })
            FilterChip(selected = false, onClick = {}, label = { Text("Diésel") })
            FilterChip(selected = false, onClick = {}, label = { Text("Eléctrico") })
        }

        HorizontalDivider()

        // Card con borde
        Card(
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, Color.Gray),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Tarjeta de ejemplo", fontWeight = FontWeight.Bold)
                Text("Subtítulo dentro de Card con BorderStroke", fontSize = 13.sp)
            }
        }
    }
}

// ============================================================================
// PREVIEW 6: SCAFFOLD COMPLETO (TopBar + BottomBar + FAB)
// ============================================================================
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "6. Scaffold Completo")
@Composable
fun PreviewScaffold() {
    Scaffold(
        modifier = Modifier.height(300.dp),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("TopAppBar") }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    text = "BottomAppBar",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Añadir")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Contenido respetando innerPadding")
        }
    }
}

