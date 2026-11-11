package com.example.myapplication

import android.R.attr.text
import android.R.id.input
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.datos.Datos
import com.example.myapplication.modelo.Bandera
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.text.NumberFormat
import kotlin.compareTo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //EjercicioClase(modifier = Modifier.padding(innerPadding))
                    //EjemploTextField(modifier = Modifier.padding(innerPadding))
                    //Ejercicio2Clase(modifier = Modifier.padding(innerPadding))
                    Ejercicio3Clase(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

// -----------------------------------------------------------------------
// Ejercicio 4 Arquitectura
// -----------------------------------------------------------------------














// -----------------------------------------------------------------------
// Ejercicio 3 Listas con Lazy de clase
// -----------------------------------------------------------------------



@Composable
fun Ejercicio3Clase(modifier: Modifier = Modifier) {
    ListaBanderas (
        lista = Datos().cargarBanderas(),
        modifier = modifier
    )
}

@Composable
fun ListaBanderas(lista : List<Bandera>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(lista) { bandera ->
                TarjetaBandera(bandera = bandera)
        }
    }
}

@Composable
fun TarjetaBandera(bandera: Bandera, modifier: Modifier = Modifier) {

    var abierto by remember { mutableStateOf(false) }

    Card(modifier = modifier) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = modifier) {
                    Text("Usuario")
                    Text("Contraseña")
                }
                Column(modifier = modifier) {
                    Image(
                        painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                TarjetaBoton(
                    abierto = abierto,
                    onClick = { abierto = !abierto}
                )
            }
            if (abierto) {
                InformacionBandera(
                    bandera.usuario,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 8.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
                )
            }
        }
    }
}

@Composable
private fun TarjetaBoton(
    abierto: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            painter = if (abierto) painterResource(R.drawable.expand_circle_up) else painterResource(R.drawable.expand_circle_down),
            contentDescription = if (abierto) stringResource((R.string.abrir)) else stringResource((R.string.cerrar))
        )
    }
}

@Composable
fun InformacionBandera(
    @StringRes info: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.Usuario),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(info),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}



// -----------------------------------------------------------------------
// Ejercicio 2 cálculo IVA de clase
// -----------------------------------------------------------------------



@Composable
fun Ejercicio2Clase(modifier: Modifier = Modifier) {
    var numero by remember {mutableStateOf("")}
    var precio = numero.toDoubleOrNull() ?: 0.0
    var preciofinal: String = " "

    val radioOptions = listOf("3", "10", "21")
    val (selectedOptions, onOptionSelected) = remember { mutableStateOf(radioOptions[0])}

    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(30.dp,100.dp,0.dp,0.dp)) {
        OutlinedTextField(
            value = numero,
            onValueChange = { numero = it },
            label = { Text(text = "Cálculo del IVA: ")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Esto hace que el teclado sea solo Numerico
            textStyle = TextStyle(color = Color.Red, fontWeight = FontWeight.Bold),

        )
        Column(modifier.selectableGroup() ) {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .selectable(
                            selected = (text == selectedOptions),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(
                        selected = (text == selectedOptions),
                        onClick = null
                    )
                    Text(
                        text = "$text%",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        }

        // Esto sirve para separar cada elemento, es decir, dejar un marger por arriba
        Spacer(modifier = Modifier.height(30.dp))

        val ivaDoble = selectedOptions.toDoubleOrNull() ?: 0.0

        preciofinal = calcularIva(precio, ivaDoble)

        Text("el precio con IVA es: $preciofinal")
    }
}
private fun calcularIva (precio: Double, iva: Double) : String {
    var iva = precio + (precio * (iva / 100))// Este es el cálculo del Iva
    return NumberFormat.getCurrencyInstance().format(iva)
}



// -----------------------------------------------------------------------
// Ejercicio de clase
// -----------------------------------------------------------------------



@Composable
fun EjercicioClase(modifier: Modifier = Modifier) {

    /*
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Column {
            Image(painterResource(id = R.drawable.icons8_user_30), contentDescription = null)
            Text("Hello")
        }
        Column {
            Text("Hello")
            Image(painterResource(id = R.drawable.icons8_user_30), contentDescription = null)
        }
    }
    */
    Column(modifier = modifier) {
        Row {
            Text("Hello")
            Image(painterResource(id = R.drawable.icons8_user_30), contentDescription = null)
        }
        Row {
            Image(painterResource(id = R.drawable.icons8_user_30), contentDescription = null)
            Text("Hello")
        }
    }
}



// -----------------------------------------------------------------------
// Ejercicio Ejemplo Text Field de clase
// -----------------------------------------------------------------------



@Composable
fun EjemploTextField(modifier: Modifier = Modifier) {
    //val nombre = stringResource(R.string.nombre)
    var input by remember { mutableStateOf("") }

    OutlinedTextField(
        value = input,
        onValueChange = { input = it },
        label = { Text(stringResource(R.string.etiqueta))},
        placeholder = { Text (stringResource(R.string.nombre))},
        modifier = modifier
    )
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

