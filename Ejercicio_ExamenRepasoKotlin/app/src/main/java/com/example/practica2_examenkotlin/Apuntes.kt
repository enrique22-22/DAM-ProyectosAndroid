package com.example.practica2_examenkotlin


/*
// ============================================================================
// 0. REGLAS Y ANOTACIONES BÁSICAS DE COMPOSE
// ============================================================================
// - @Composable: Función que pinta interfaz. No devuelve nada (Unit).
// - Nombre de función @Composable: Siempre en MAYÚSCULA y debe ser un SUSTANTIVO
//   (ej. PerfilUsuario, TarjetaCoche). Nunca un verbo ni adjetivo (DibujarPerfil).
// - @Preview(showBackground = true): Vista previa en Android Studio sin ejecutar.

@Preview(showBackground = true)
@Composable
fun VistaPrevia() {
    TuPantalla()
}

// ============================================================================
// 1. SCAFFOLD (ESTRUCTURA PRINCIPAL DE PANTALLA)
// ============================================================================

// --- Scaffold básico (el que crea Android Studio por defecto) ---
Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
    TuPantalla(modifier = Modifier.padding(innerPadding))
}

// --- Scaffold completo (TopBar + BottomBar + FloatingActionButton) ---
Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            ),
            title = { Text(text = "Título superior") }
        )
    },
    bottomBar = {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Text(
                text = "Barra inferior",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    },
    floatingActionButton = {
        FloatingActionButton(onClick = { /* Acción */ }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Añadir")
        }
    }
) { innerPadding ->
    Column(modifier = Modifier.padding(innerPadding)) {
        // Contenido de la pantalla
    }
}

// ============================================================================
// 2. TEXTOS (Text) Y TODOS SUS ESTILOS
// ============================================================================

// --- Texto desde strings.xml (para multi-idioma / localización) ---
Text(text = stringResource(id = R.string.app_name))

// --- Las 5 formas de poner color a un Text ---
Text(text = "Color predefinido", color = Color.Blue)
Text(text = "Color hexadecimal", color = Color(0xFFD0BCFF))
Text(text = "Color desde colors.xml", color = colorResource(id = R.color.teal_700))
Text(text = "Color desde Color.kt", color = Purple80)
Text(text = "Color del tema (Recomendado)", color = MaterialTheme.colorScheme.primary)

// --- Texto completo con tamaño (.sp), negrita, cursiva, alineación y altura de línea ---
Text(
    text = "Texto de ejemplo",
    color = MaterialTheme.colorScheme.primary,
    fontSize = 24.sp,                           // Siempre en .sp
    fontWeight = FontWeight.Bold,               // Bold, Medium, SemiBold, Light, Normal
    fontStyle = FontStyle.Italic,               // Italic o Normal
    textAlign = TextAlign.Center,               // Start, End, Center, Justify (evitar Left/Right)
    lineHeight = 32.sp,                         // Separación entre líneas
    maxLines = 2,                               // Máximo de líneas
    overflow = TextOverflow.Ellipsis,           // Puntos suspensivos si se corta
    modifier = Modifier.fillMaxWidth()          // Necesario para que textAlign funcione
)

// ============================================================================
// 3. CAMPOS DE TEXTO (TextField y OutlinedTextField)
// ============================================================================

// --- TextField (con fondo relleno) ---
TextField(
    value = texto,
    onValueChange = { texto = it },
    label = { Text("Etiqueta") },
    singleLine = true
)

// --- OutlinedTextField (con borde) + Teclado Numérico ---
OutlinedTextField(
    value = horas,
    onValueChange = { viewModel.actualizarHoras(it) },
    label = { Text("Horas") },
    placeholder = { Text("Ej: 5") },
    singleLine = true,
    keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number      // Number, Text, Email, Password, Phone
    ),
    textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
    modifier = Modifier.fillMaxWidth()
)

// --- OutlinedTextField con mayúsculas automáticas y error en ROJO ---
OutlinedTextField(
    value = uiState.matricula,
    onValueChange = { viewModel.actualizarMatricula(it) },
    label = { Text("Matrícula") },
    isError = uiState.matriculaConError,        // Pone borde y label en rojo si es true
    colors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = if (uiState.matriculaConError) Color.Red else MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = if (uiState.matriculaConError) Color.Red else MaterialTheme.colorScheme.onSurface
    ),
    keyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.Characters
    ),
    singleLine = true,
    modifier = Modifier.fillMaxWidth()
)

// --- OutlinedTextField deshabilitado (solo lectura, ej. para mostrar fecha) ---
OutlinedTextField(
    value = viewModel.fecha,
    onValueChange = {},
    enabled = false,
    label = { Text("Fecha") },
    modifier = Modifier.fillMaxWidth()
)

// ============================================================================
// 4. IMÁGENES (Image) E ICONOS (Icon) CON DIFERENTES MODIFICADORES
// ============================================================================
// Nota: Al importar imágenes en Resource Manager -> + -> Import Drawables,
// elegir Qualifier Type: Density -> Value: No Density para que no se vean borrosas.

// --- Imagen básica (contentDescription = null si es decorativa) ---
Image(
    painter = painterResource(id = R.drawable.mi_imagen),
    contentDescription = null
)

// --- Imagen de fondo a pantalla completa con opacidad (alpha) y recorte (Crop) ---
Image(
    painter = painterResource(id = R.drawable.mi_imagen),
    contentDescription = null,
    contentScale = ContentScale.Crop,           // Crop, Fit, FillBounds, FillWidth, FillHeight, Inside
    alpha = 0.5f,                               // Opacidad: de 0.0f (invisible) a 1.0f (opaca)
    modifier = Modifier.fillMaxSize()
)

// --- Imagen cuadrada / icono de tamaño fijo ---
Image(
    painter = painterResource(id = R.drawable.coche),
    contentDescription = "Coche",
    contentScale = ContentScale.Fit,
    modifier = Modifier
        .size(48.dp)
        .padding(4.dp)
)

// --- Imagen circular (Avatar) con borde ---
// IMPORTANTE EL ORDEN: 1. size -> 2. clip -> 3. background -> 4. border
Image(
    painter = painterResource(id = R.drawable.usuario),
    contentDescription = "Avatar",
    contentScale = ContentScale.Crop,
    modifier = Modifier
        .size(80.dp)
        .clip(CircleShape)
        .background(Color.LightGray)
        .border(2.dp, Color.Black, CircleShape)
)

// --- Imagen rectangular con esquinas redondeadas ---
Image(
    painter = painterResource(id = R.drawable.banner),
    contentDescription = "Banner",
    contentScale = ContentScale.Crop,
    modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .clip(RoundedCornerShape(16.dp))
        .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
)

// --- Iconos (Material Icons o Drawable propio) ---
Icon(
    imageVector = Icons.Filled.CheckCircle,
    contentDescription = "Icono Material",
    tint = Color.Green,
    modifier = Modifier.size(24.dp)
)

Icon(
    painter = painterResource(id = R.drawable.expand_circle_down),
    contentDescription = "Desplegar",
    tint = MaterialTheme.colorScheme.primary
)

// ============================================================================
// 5. LAYOUTS: COLUMN, ROW Y BOX
// ============================================================================

// --- COLUMN (Vertical: uno debajo de otro) ---
Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),  // Top, Center, Bottom, SpaceBetween, SpaceEvenly, SpaceAround
    horizontalAlignment = Alignment.CenterHorizontally  // Start, CenterHorizontally, End
) {
    Text("Arriba")
    Text("Abajo")
}

// --- ROW (Horizontal: uno al lado de otro) ---
Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
    horizontalArrangement = Arrangement.SpaceBetween,   // Start, Center, End, SpaceBetween, SpaceEvenly, SpaceAround, spacedBy(8.dp)
    verticalAlignment = Alignment.CenterVertically      // Top, CenterVertically, Bottom
) {
    Text("Izquierda")
    Text("Derecha")
}

// --- BOX (Superposición: uno encima de otro en eje Z) ---
Box(
    modifier = Modifier.size(120.dp),
    contentAlignment = Alignment.Center                 // Alineación general de los hijos
) {
    Image(
        painter = painterResource(id = R.drawable.fondo),
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
    Icon(
        imageVector = Icons.Filled.CheckCircle,
        contentDescription = null,
        modifier = Modifier.align(Alignment.TopStart)   // TopStart, TopEnd, BottomStart, BottomEnd, Center
    )
    Text(
        text = "Etiqueta",
        modifier = Modifier.align(Alignment.BottomEnd)
    )
}

// --- Separadores y Espacios ---
Spacer(modifier = Modifier.height(12.dp))               // Espacio vertical (dentro de Column)
Spacer(modifier = Modifier.width(12.dp))                // Espacio horizontal (dentro de Row)
HorizontalDivider(thickness = 1.dp, color = Color.Gray) // Línea divisoria horizontal

// ============================================================================
// 6. MODIFICADORES (Modifier) Y LA REGLA DEL ORDEN
// ============================================================================

// --- Reparto de espacio proporcional (weight) ---
// Dentro de un Row o Column, weight(0.5f) y weight(0.5f) dividen al 50%
Row(modifier = Modifier.fillMaxWidth()) {
    Column(modifier = Modifier.weight(0.5f)) { /* Columna izquierda 50% */ }
    Column(modifier = Modifier.weight(0.5f)) { /* Columna derecha 50% */ }
}

// --- Orden de .clickable y .padding ---
// Clicable CON padding (toda el área incluida la margen responde al clic):
Modifier
    .clickable(onClick = { /* Acción */ })
    .padding(16.dp)
    .fillMaxWidth()

// Clicable SIN padding (solo el contenido interior responde al clic):
Modifier
    .padding(16.dp)
    .clickable(onClick = { /* Acción */ })
    .fillMaxWidth()

// ============================================================================
// 7. BOTONES Y CONTROLES DE FORMULARIO
// ============================================================================

// --- Botón estándar ---
Button(
    onClick = { viewModel.registrar() },
    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
) {
    Text(text = "Guardar")
}

// --- Botón con borde / Botón solo texto / Botón icono ---
OutlinedButton(onClick = { /* Acción */ }) { Text("Cancelar") }
TextButton(onClick = { /* Acción */ }) { Text("Aceptar") }
IconButton(onClick = { /* Acción */ }) {
    Icon(painter = painterResource(id = R.drawable.mi_icono), contentDescription = null)
}

// --- RadioButton (Selección única) ---
Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.clickable { viewModel.seleccionar("Coche") }
) {
    RadioButton(
        selected = uiState.seleccionado == "Coche",
        onClick = { viewModel.seleccionar("Coche") }
    )
    Text(text = "Coche")
}

// --- Checkbox (Casilla de verificación) ---
Row(verticalAlignment = Alignment.CenterVertically) {
    Checkbox(
        checked = uiState.esUrgente,
        onCheckedChange = { viewModel.actualizarUrgencia(it) }
    )
    Text(text = "¿Es urgente?")
}

// --- Switch (Interruptor) ---
Row(verticalAlignment = Alignment.CenterVertically) {
    Text(text = "¿Es refrigerada?")
    Switch(
        checked = uiState.esRefrigerada,
        onCheckedChange = { viewModel.actualizarRefrigerada(it) }
    )
}

// --- FilterChip (Etiqueta seleccionable) ---
FilterChip(
    selected = uiState.motor == "Gasolina",
    onClick = { viewModel.actualizarMotor("Gasolina") },
    label = { Text(text = "Gasolina") }
)

// ============================================================================
// 8. CARDS, LISTAS (LazyColumn / LazyRow) Y ALERTDIALOG
// ============================================================================

// --- Card con color y borde ---
Card(
    modifier = Modifier.fillMaxWidth(),
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant // o errorContainer
    ),
    border = BorderStroke(1.dp, Color.Gray)
) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(text = "Contenido de la tarjeta")
    }
}

// --- LazyColumn (Lista vertical con scroll) ---
LazyColumn(
    modifier = Modifier
        .fillMaxWidth()
        .weight(1f),
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    items(uiState.listaElementos) { elemento ->
        Text(text = elemento.nombre)
    }
}

// --- LazyRow (Lista horizontal con scroll) ---
LazyRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(8.dp)
) {
    items(uiState.listaElementos) { elemento ->
        Text(text = elemento.nombre)
    }
}

// --- AlertDialog (Ventana emergente) ---
if (uiState.mostrarAlerta) {
    AlertDialog(
        onDismissRequest = { viewModel.cerrarAlerta() },
        title = { Text(text = "Aviso", fontWeight = FontWeight.Bold) },
        text = { Text(text = uiState.mensajeAlerta) },
        confirmButton = {
            TextButton(onClick = { viewModel.cerrarAlerta() }) {
                Text(text = "Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = { viewModel.cerrarAlerta() }) {
                Text(text = "Cancelar")
            }
        }
    )
}

// ============================================================================
// 9. SNIPPETS RÁPIDOS DE VIEWMODEL, FECHAS Y VALIDACIONES
// ============================================================================

// --- Leer UIState en el Main ---
val uiState by viewModel.uiState.collectAsState()

// --- Actualizar UIState en el ViewModel (.update + .copy) ---
_uiState.update { estadoActual ->
    estadoActual.copy(
        campo = nuevoValor,
        lista = estadoActual.lista + nuevoElemento
    )
}

// --- Fecha actual del sistema ---
@RequiresApi(Build.VERSION_CODES.O)
val fecha: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

// --- Validaciones habituales ---
val soloNumeros = texto.all { it.isDigit() }
val numeroEntero = texto.toIntOrNull() // devuelve null si no es número válido
val matriculaValida = Regex("^[0-9]{4}[A-Z]{3}$").matches(matricula.trim())

// --- Localización (Multi-idioma en res/) ---
// res/values/strings.xml    -> Por defecto (Inglés)
// res/values-es/strings.xml -> Español (ISO 639-1)
// res/values-fr/strings.xml -> Francés (ISO 639-1)
*/