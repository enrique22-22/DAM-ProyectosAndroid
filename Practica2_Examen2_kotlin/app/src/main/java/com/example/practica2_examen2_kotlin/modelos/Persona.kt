package com.example.practica2_examen2_kotlin.modelos

import androidx.annotation.DrawableRes

open class Persona(
    val rol: String,
    @DrawableRes val imagen: Int
)