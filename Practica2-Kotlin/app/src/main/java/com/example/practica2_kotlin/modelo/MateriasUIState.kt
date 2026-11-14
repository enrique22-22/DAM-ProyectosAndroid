package com.example.practica2_kotlin.modelo

data class MateriasUIState (
    val tareas: List<Tarea> = listOf(),
    val tareasUrgentes: List<Tarea> = listOf(),
    val tareaUIState: Tarea = Tarea()
)
