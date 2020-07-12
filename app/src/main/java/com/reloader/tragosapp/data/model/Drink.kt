package com.reloader.tragosapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    val imagen: String = "",
    val nombre: String = "",
    val descripcion: String = ""
) : Parcelable
// te permite enviar un objeto completo y lo podes utilizar para un bundle