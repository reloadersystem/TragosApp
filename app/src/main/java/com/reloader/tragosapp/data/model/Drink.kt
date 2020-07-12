package com.reloader.tragosapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    @SerializedName("strDrinkThumb") // mi modelo armado pero para no hacer jsonObject = getJsonObject al serializar , lo busca  y trae esos datos con ese nombre
    val imagen: String = "",
    @SerializedName("strDrink")
    val nombre: String = "",
    @SerializedName("strInstructions")
    val descripcion: String = ""
) : Parcelable
// te permite enviar un objeto completo y lo podes utilizar para un bundle


data class DrinkList(
    @SerializedName("drinks") //  es el contenedor JSONArray
    val drinkList: List<Drink>)