package com.example.myapplication.modelo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Bandera(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imagenResourceId: Int,
    @StringRes val usuario: Int

)
